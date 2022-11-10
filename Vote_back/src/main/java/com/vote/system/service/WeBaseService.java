package com.vote.system.service;

import cn.hutool.core.lang.Dict;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.*;
import com.vote.system.utils.IOUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeBaseService {
    @Value("${system.contract.VoteAddress}")
    String contractAddress;

    @Value("${fisco.server.url}")
    String fiscoUrl;

    public static final String ABI = IOUtil.readResourceAsString("com/vote/system/abi/Vote.abi");

    public static String userAddress = "";

    public Dict haveAccount(String account) {
        userAddress = account;

        List funcParam = new ArrayList();
        funcParam.add(account);
        Dict result = commonReq(userAddress,"haveAccount",funcParam);
        return result;
    }

    public Dict getAllproject(String account) {
        List funcParam = new ArrayList();
        Dict result = commonReq(account,"getAllproject",funcParam);
        return result;
    }

    Dict commonReq(String userAddress, String funcName, List funcParam) {
        JSONArray abiJSON = JSONUtil.parseArray(ABI);
        JSONObject data = JSONUtil.createObj();
        data.set("groupId","1");
        data.set("user",userAddress);
        data.set("contractName","Vote");
        data.set("version","");
        data.set("funcName",funcName);
        data.set("funcParam",funcParam);
        data.set("contractAddress",contractAddress);
        data.set("contractAbi",abiJSON);
        data.set("useAes",false);
        data.set("useCns",false);
        data.set("cnsName","");

        String dataString = JSONUtil.toJsonStr(data);
        String responseBody = HttpRequest.post(fiscoUrl+"/WeBASE-Front/trans/handle")
                .header(Header.CONTENT_TYPE,"application/json")
                .body(dataString)
                .execute()
                .body();
        Dict retDict = new Dict();
        retDict.set("result",responseBody);
        return retDict;
    }

    public Dict listDeployedContract() {
        JSONObject data = JSONUtil.createObj();
        data.set("groupId","1");
        data.set("contractName","");
        data.set("contractStatus",2);
        data.set("contractAddress","");
        data.set("pageNumber",1);
        data.set("pageSize",10);

        String dataString = JSONUtil.toJsonStr(data);
        String responseBody = HttpRequest.post(fiscoUrl+"/WeBASE-Front/contract/contractList")
                .header(Header.CONTENT_TYPE,"application/json")
                .body(dataString)
                .execute()
                .body();
        JSONObject bodyJSON = JSONUtil.parseObj(responseBody);
        JSONArray constractArray = JSONUtil.parseArray(bodyJSON.get("data"));
        List<Object> retArr = constractArray.stream().map(obj -> {
            JSONObject rawObj = (JSONObject) obj;
            JSONObject json = new JSONObject();
            json.set("合约名称",rawObj.get("contractName"));
            json.set("合约地址",rawObj.get("contractAddress"));
            json.set("部署时间",rawObj.get("deployTime"));
            json.set("创建时间",rawObj.get("createTime"));
            json.set("修改时间",rawObj.get("ModifyTime"));
            json.set("abi",rawObj.get("contractAbi"));
            return json;
        }).collect(Collectors.toList());

        Dict retDict = new Dict();
        retDict.set("result",retArr);
        return retDict;
    }
}
