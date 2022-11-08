pragma solidity ^0.4.24;
pragma experimental ABIEncoderV2;

contract Vote {
    struct Project{
        string name;
        uint128 projectID;
        string[] memebers;
        mapping(string => uint64) poll;
        mapping(string => address[]) investor;
        bool survival;
        address owner;
        string passwd;
    }
    
    struct Person {
        uint128[] allProject;
        string[] projectName;
    }
    
    mapping(uint128 => Project) voteProject;
    mapping(address => Person) personProject;
    address[] allUser;
    uint128 allProject;
    
    address owner;
    
    constructor() {
        owner = msg.sender;
    }
    
    modifier notOwner(address account) {
        require(account != owner,"Owner can't use it.");
        _;
    }
    
    modifier ontProjectOwner(uint128 projectID,address account) {
        require(voteProject[projectID].owner != account,"Owner can't poll");
        _;
    }
    
    function haveAccount(address account) public view returns(bool){
        for(uint i = 0; i < allUser.length; i++){
            if(allUser[i] == account){
                return true;
            }
        }
        return false;
    }
    
    function signAccount(address account)public   returns(bool) {
        require(!haveAccount(account),"Account was exist.");
        allUser.push(account);
        return true;
    }
    
    function canSignOne(string projectName,address account) public  returns(bool) {
        for(uint i = 0; i < personProject[account].allProject.length; i++) {
            uint128 nowID = personProject[account].allProject[i];
            if(keccak256(voteProject[nowID].name) == keccak256(projectName)){
                if(voteProject[nowID].owner == account) {
                    if(voteProject[nowID].survival) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    function sginProject(string projectName,address account,string password) public notOwner(account) returns(uint128) {
        require(canSignOne(projectName,account),"You can't sign the project.");
        require(haveAccount(account),"Account is not exist.");
        voteProject[allProject].name = projectName;
        voteProject[allProject].projectID = allProject;
        uint128 theProjectID = allProject;
        voteProject[allProject].owner = account;
        voteProject[allProject].survival = true;
        voteProject[allProject].passwd = password;
        personProject[account].allProject.push(allProject);
        personProject[account].projectName.push(projectName);
        allProject += 1;
        return theProjectID;
    }
    
    function addProjectMemeber(uint128 projectID,string[] memeber,address account) public  returns(bool){
        require(voteProject[projectID].owner == account,"You are not owner.");
        require(voteProject[projectID].survival,"The project was off.");
        for(uint i = 0;i < memeber.length;i++){
            voteProject[projectID].poll[memeber[i]] = 0;
            voteProject[projectID].memebers.push(memeber[i]);
        }
        return true;
    }
    
    function pollMemeber(uint128 projectID,string member,string passwd,address account) ontProjectOwner(projectID,account)  returns(bool){
        require(haveAccount(account),"Account is not exist.");
        require(voteProject[projectID].survival,"The project was off.");
        require(keccak256(voteProject[projectID].passwd) == keccak256(passwd),"password is error.");
        voteProject[projectID].poll[member] += 1;
        voteProject[projectID].investor[member].push(account);
        return true;
    }
    
    function offTheProject(uint128 projectID,address account,string passwd) public  returns(bool){
        require(voteProject[projectID].owner == account,"You are not owner.");
        require(keccak256(voteProject[projectID].passwd) == keccak256(passwd),"password is error.");
        voteProject[projectID].survival = false;
        return true;
    }
    
    function checkeResult(uint128 projectID) public   returns(string[],uint64[]){
        string[] allMemeber = voteProject[projectID].memebers;
        uint64[] allPoll;
        for(uint i = 0; i < allMemeber.length; i++){
            allPoll.push(voteProject[projectID].poll[allMemeber[i]]);
        }
        return (allMemeber,allPoll);
    }
    
    function checkeMyProject(address account) public view returns(uint128[],string[]){
        return (personProject[account].allProject,personProject[account].projectName);
    }
    
    function getAllproject() public view returns(uint128) {
        uint128 result;
        for(uint128 i = 0; i < allProject; i++){
            if(voteProject[i].survival){
                result += 1;
            }
        }
        return result;
    }
    
}
