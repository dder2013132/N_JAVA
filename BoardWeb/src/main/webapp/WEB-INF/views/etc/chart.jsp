<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      async function drawChart() {
        let dataArr = [];
        dataArr.push(['작성자', '작성자 별 게시글 수']);
        let result = await fetch('chartJson.do');
        let result2 = await result.json();
        result2.forEach(item => {
          console.log(item);
          dataArr.push([item.writer, item.cnt]);
        })

        var data = google.visualization.arrayToDataTable(dataArr);

        var options = {
          title: '게시글 작성자 비율'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>


    <div id="piechart" style="width: 900px; height: 500px;"></div>

