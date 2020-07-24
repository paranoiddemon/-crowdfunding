<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<script src="jquery/jquery-2.1.1.min.js" type="text/javascript"></script> <%--引入jquery 使用ajax--%>
<script src="layer/layer.js" type="text/javascript"></script>

    <script>
        $(function () {
            $("#btn1").click(function () {
               /* $.get()
                $.post()   这两个方法都必须是返回状态码200的时候才会返回，$.ajax()则返回其他也行*/
                $.ajax({
                   "url": "send/array.html",  //请求目标资源的地址
                   "type": "post",          //请求方式
                   "data": {
                       "array": [1,42,12] //发送给服务器的数据，请求参数
                   },
                   "dataType": "text", //服务端返回的数据格式怎么解析
                   "success": function(response) {   //成功处理请求后调用的回调函数，response是响应体数据
                        alert(response);
                } ,
                    "error": function (response) { //处理请求失败的回调函数
                        alert(response);
                    }
                });
            });
        });
    </script>
    <script>
        $(function () {
            $("#btn3").click(function () {
                var array = [1,32,13];
                var requestBody = JSON.stringify(array);  //把JSON数组转成JSON字符串 "['1','32','13']"
                /* $.get()
                 $.post()   这两个方法都必须是返回状态码200的时候才会返回，$.ajax()则返回其他也行*/
                $.ajax({
                    "url": "send/array3.html",  //请求目标资源的地址
                    "type": "post",          //请求方式
                    "contentType": "application/json;charset=UTF-8", //告诉服务器本次请求的请求体是json数据
                    "data": requestBody,  //请求体
                    "dataType": "text", //服务端返回的数据格式怎么解析
                    "success": function(response) {   //成功处理请求后调用的回调函数，response是响应体数据
                        alert(response);
                    } ,
                    "error": function (response) { //处理请求失败的回调函数
                        alert(response);
                    }
                });
            });
        });
    </script>
    <script>
        $(function () {
            $("#btn4").click(function () {
                //传一个student对象
                var student = {
                    "stuId": 4,
                    "stuName": "tom",
                    "address": {
                        "province": "广东",
                        "city": "深圳",
                        "street": "后海"
                    },
                    "subjectList":[
                        {"subjectName":"java","subjectScore":100},
                        {"subjectName":"php","subjectScore":20}
                        ],
                    "map":{
                        "k1":"v1",
                        "k2":"v2"
                    }
                };
                //把json对象转换成一个json字符串
                var requestBody =  JSON.stringify(student);
                /* $.get()
                 $.post()   这两个方法都必须是返回状态码200的时候才会返回，$.ajax()则返回其他也行*/
                $.ajax({
                    "url": "send/compose/object.html",  //请求目标资源的地址,基于base的，前面用“/”
                    "type": "post",          //请求方式
                    "contentType": "application/json;charset=UTF-8", //告诉服务器本次请求的请求体是json数据
                    "data": requestBody,  //请求体
                    "dataType": "text", //服务端返回的数据格式怎么解析
                    "success": function(response) {   //成功处理请求后调用的回调函数，response是响应体数据
                        alert(response);
                    } ,
                    "error": function (response) { //处理请求失败的回调函数
                        alert(response);
                    }
                });
            });
        });
    </script>
    <script>
        $(function () {
            $("#btn5").click(function () {
                //传一个student对象
                var student = {
                    "stuId": 4,
                    "stuName": "tom",
                    "address": {
                        "province": "广东",
                        "city": "深圳",
                        "street": "后海"
                    },
                    "subjectList":[
                        {"subjectName":"java","subjectScore":100},
                        {"subjectName":"php","subjectScore":20}
                        ],
                    "map":{
                        "k1":"v1",
                        "k2":"v2"
                    }
                };
                //把json对象转换成一个json字符串
                var requestBody =  JSON.stringify(student);
                /* $.get()
                 $.post()   这两个方法都必须是返回状态码200的时候才会返回，$.ajax()则返回其他也行*/
                $.ajax({
                    "url": "send/compose/object.json",  //这里要改成json后缀
                    "type": "post",          //请求方式
                    "contentType": "application/json;charset=UTF-8", //告诉服务器本次请求的请求体是json数据
                    "data": requestBody,  //请求体
                    "dataType": "json", //因为后端返回的是对象，且已经被转为Json，让浏览器解析
                    "success": function(response) {   //成功处理请求后调用的回调函数，response是响应体数据
                        console.log(response); //在浏览器的控制台打印
                    } ,
                    "error": function (response) { //处理请求失败的回调函数
                        console.log(response); //在浏览器的控制台打印
                    }
                });
            });
        });
    </script>
    <script>
        $(function () {
            $("#btn6").click(function () {
                layer.msg("here is a msg from layer");
            });
        });
    </script>

</head>
<body>
<h1>hello world</h1>

<a href="test/ssm.html">SSM整合</a>

<hr>
<button id="btn1">Test Request Body1</button>
<hr>
<button id="btn2">Test Request Body2</button>

<hr>
<button id="btn3">Test Request Body3</button>

<hr>
<button id="btn4">发送复杂对象</button>

<hr>
<button id="btn5">返回统一对象</button>

<hr>
<button id="btn6">layer弹框测试</button>

</body>
</html>
