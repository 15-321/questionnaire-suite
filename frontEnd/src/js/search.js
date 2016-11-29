/*Created by u1995 on 2016/11/28.*/
$(function () {
    var searchBtn=$('#searchBtn');
    var exportBtn=$('#exportBtn');
    //获取数据
    var tableSearch=$('#tableSearch');
    searchBtn.click(function(){
        var conditions={
            school:$('#school').val(),
            major:$('#major').val(),
            nation:$('#nation').val(),
            census:$('#census').val(),
            degree:$('#degree').val(),
            sex:$('#sex').val(),
            complete:$('#complete').val()
        }
        $.ajax({
            url:'#',
            data:conditions,
            success:function(data){
                var jsondata=$.parseJSON(data);
                var html="";
                tableSearch.html("");
                for(var i=0;i<jsondata.length;i++){
                    html="<tr><td>"+jsondata[i].school+"</td><td>"+jsondata[i].major+"</td><td>"+jsondata[i].nation+"</td><td>"+jsondata[i].census+"</td><td>"+jsondata[i].degree+"</td><td>"+jsondata[i].sex+"</td><td>"+jsondata[i].complete+"</td></tr>";
                    tableSearch.append(html);
                }
            }
        });
    });
    //导出
    exportBtn.click(function () {

    });
});