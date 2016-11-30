/*Created by u1995 on 2016/11/28.*/
$(function () {
    var searchBtn=$('#searchBtn');
    var exportPDFBtn=$('#exportPDFBtn');
    var exportExcelBtn=$('#exportExcelBtn');
    //获取数据
    var tableSearch=$('#tableSearch tbody');
    var action=function(url){
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
                    exportPDFBtn.attr('href',function(){return this.href+="&condition="+encodeURI(conditions.condition)});
                    exportExcelBtn.attr('href',function(){return this.href+="&condition="+encodeURI(conditions.condition)});
                }
            }
        });
    };
    searchBtn.click(function(e){
        e.preventDefault();
        action('http://localhost:8080/QuestionnaireSuite/QueryServlet?operation=query');
    });
    //导出PDF
    // exportPDFBtn.click(function () {
    //     action('http://localhost:8080/QuestionnaireSuite/QueryServlet?operation=pdf');
    // });
    //导出Excel
    // exportExcelBtn.click(function () {
    //     action('http://localhost:8080/QuestionnaireSuite/QueryServlet?operation=excel');
    // });
});