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
    //查询IP
    searchBtn.click(function(e){
        e.preventDefault();
        var city=$('#city').val();
        $.ajax({
            url:'234.txt',
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
    var innitData=function(){
        //系统状态
        $.ajax({
            url:'123.txt',
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
                IPNumber.text(jsondata.num);
                // alert(IPNumber.innerHTML)
            }
        });
    }
    innitData();
    var timer=setInterval(innitData,10000);
})