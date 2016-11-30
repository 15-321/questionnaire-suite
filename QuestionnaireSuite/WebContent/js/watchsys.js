/*Created by jiangwenyang on 2016/11/28.*/
$(function () {
    var tableSysItem=$('#tableSys tr');
    var IPNumber=$('#IPNumber');
    var span_sysData=[];
    for(var i=0;i<tableSysItem.length;i++){
        span_sysData[i]=$(tableSysItem[i]).find('span');
    }
    var searchBtn=$('#searchBtn');
    var IPlist=$('#IPlist');
    var cityCurrent=$('#cityCurrent');
    //查询IP
    searchBtn.click(function(e){
        e.preventDefault();
        var city=$('#city').val();
        cityCurrent.text(city);
        $.ajax({
            url:'http://localhost:8080/QuestionnaireSuite/WatchIPServlet',
            data:{
                "district":city
            },
            success:function(data){
                var jsondata=$.parseJSON(data);
                if(!jsondata){
                    var item="<p class='text-danger'>暂时没有查询结果</p>";
                    IPlist.html('');
                    IPlist.append(item);
                }else{
                    IPlist.html('');
                    for(var i=0;i<jsondata.length;i++){
                        var item="<li>"+jsondata[i]+"</li>";
                        IPlist.append(item);
                    }
                }
            }
        });
    });
    //查询系统状态请求
    var watchSys=function(){
        //系统状态
        $.ajax({
            url:'http://localhost:8080/QuestionnaireSuite/WatchServerServlet',
            success:function(data){
                var jsondata=$.parseJSON(data).condition;
                span_sysData[0][0].innerHTML=jsondata.si;
                span_sysData[0][1].innerHTML=jsondata.so;
                span_sysData[1][0].innerHTML=jsondata.bi;
                span_sysData[1][1].innerHTML=jsondata.bo;
                span_sysData[2][0].innerHTML=jsondata.in;
                span_sysData[2][1].innerHTML=jsondata.cs;
                span_sysData[3][0].innerHTML=jsondata.us;
                span_sysData[3][1].innerHTML=jsondata.sy;
                span_sysData[3][2].innerHTML=jsondata.id;
                span_sysData[3][3].innerHTML=jsondata.wa;
                span_sysData[4][0].innerHTML=jsondata.r;
                span_sysData[4][1].innerHTML=jsondata.b;
                span_sysData[4][2].innerHTML=jsondata.wspd;
                span_sysData[4][3].innerHTML=jsondata.free;
                span_sysData[4][4].innerHTML=jsondata.buff;
                span_sysData[4][5].innerHTML=jsondata.cache;
            }
        });
    }
    //查询在线人数请求
    var wtachIP=function(){
        $.ajax({
            url:"http://localhost:8080/QuestionnaireSuite/WatchIPServlet?act=getNum",
            type:"GET",
            success:function(data){
                IPNumber.text(data);
            }
        })
    }
    //初始化系统状态和IP数
    var innitData=function(){
        watchSys();
        wtachIP();
    };
    innitData();
    var watchSysTimer=setInterval(watchSys,1000);
    var watchIPTimer=setInterval(wtachIP,10000);
})