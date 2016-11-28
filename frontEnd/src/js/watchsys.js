/*Created by jiangwenyang on 2016/11/28.*/
$(function () {
    var innitData=function(){
        $.ajax({
            url:'#',
            success:function(data){

            }
        });
        $.ajax({
            url:'#',
            success:function(data){

            }
        });
    }
    innitData();
    var timer=setInterval(innitData,5000);
})