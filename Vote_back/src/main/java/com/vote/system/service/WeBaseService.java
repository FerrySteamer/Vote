package com.vote.system.service;

import cn.hutool.core.lang.Dict;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.*;
import com.vote.system.utils.IOUtil;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public class WeBaseService {
    @Value("${system.contract.srinGetSetAddress}")
    String contractAddress;

    @Value("${fisco.server.url}")
    String fiscoUrl;

    public static final String ABI = IOUtil.readResourceAsString("abi/StringGetSet.abi");

    public Dict newGet(String userAddress) {
        List funcParam = new ArrayList();
        Dict result = commonReq(userAddress,"get",funcParam);
        return result;
    }

    Dict commonReq(String userAddress, String funcName, List funcParam) {
        JSONArray abiJSON = JSONUtil.parseArray(ABI);
        JSONObject data = JSONUtil.createObj();
        data.set("groupId","1");
        data.set("user",userAddress);
        data.set("contractName","StringGetSet");
        data.set("version","");
        data.set("funcName",funcName);
        data.set("funcParam",funcParam);
        data.set("contractAddress",contractAddress);
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

    


}
