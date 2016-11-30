/*Created by u1995 on 2016/11/28.*/
$(function () {
    var searchBtn=$('#searchBtn');
    var exportPDFBtn=$('#exportPDFBtn');
    var exportExcelBtn=$('#exportExcelBtn');
    exportPDFBtn.hide();
    exportExcelBtn.hide();
    //获取数据
    var tableSearch=$('#tableSearch tbody');
    var action=function(url){
        //获取查询参数进行编码
        var conditions={
            condition:JSON.stringify({
                school:$('#school').val(),
                major:$('#major').val(),
                nation:$('#nation').val(),
                census:$('#census').val(),
                degree:$('#degree').val(),
                sex:$('#sex').val(),
                complete:$('#complete').val()
            })
        }
        //查询请求
        $.ajax({
            url:url,
            type:'GET',
            data:conditions,
            success:function(data){
                var jsondata=$.parseJSON(data);
                var html="";
                tableSearch.html("");
                for(var i=0;i<jsondata.length;i++){
                    if(jsondata[i].nation==1){
                        jsondata[i].nation="汉族";
                    }
                    jsondata[i].sex=jsondata[i].sex=="male"?"男":"女";
                    switch(jsondata[i].degree){
                        case 0:
                            jsondata[i].degree='专科';
                            break;
                        case 1:
                            jsondata[i].degree="本科";
                            break;
                        case 2:
                            jsondata[i].degree="硕士";
                            break;
                        case 3:
                            jsondata[i].degree="博士";
                            break;
                    }
                    html="<tr><td>"+jsondata[i].school+"</td><td>"+jsondata[i].major+"</td><td>"+jsondata[i].name+"</td><td>"+jsondata[i].nation+"</td><td>"+jsondata[i].census+"</td><td>"+jsondata[i].degree+"</td><td>"+jsondata[i].sex+"</td></tr>";
                    tableSearch.append(html);
                    //下载链接同步修改
                    exportPDFBtn.show();
                    exportExcelBtn.show();
                    exportPDFBtn.attr('href',function(){var text=this.href;text+="&condition="+encodeURI(conditions.condition);return text;});
                    exportExcelBtn.attr('href',function(){var text=this.href;text+="&condition="+encodeURI(conditions.condition);return text;});
                }
            }
        });
    };
    //点击进行查询
    searchBtn.click(function(e){
        e.preventDefault();
        action('http://localhost:8080/QuestionnaireSuite/QueryServlet?operation=query');
    });
});