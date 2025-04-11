<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>

<body>
    <button id="button">버튼</button>
    <script>
        // 비동기 -> 동기
        document.querySelector('#button').addEventListener('click', async function () {
            let result = await fetch('eventList.do');
            let resolve = await result.json();
            console.log(resolve);
        });
    </script>
</body>

</html>