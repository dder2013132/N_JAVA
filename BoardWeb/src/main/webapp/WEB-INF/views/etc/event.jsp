<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }

</style>
<div id='calendar'></div>
<!-- FullCallendar -->
<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    
    var today = new Date();

    var year = today.getFullYear();
    var month = ('0' + (today.getMonth() + 1)).slice(-2);
    var day = ('0' + today.getDate()).slice(-2);

    var dateString = year + '-' + month  + '-' + day;

    var events = [];
    fetch('eventList.do')
    .then(result => result.json())
    .then(result => {
        console.log(result);
    	eventListCallback(result);    
    })
    .catch(err => console.error(err));
    
    function eventListCallback(result) {
        events = result.data;
        console.log(events);
    
	    var calendar = new FullCalendar.Calendar(calendarEl, {
	      headerToolbar: {
	        left: 'prev,next today',
	        center: 'title',
	        right: 'dayGridMonth,timeGridWeek,timeGridDay'
	      },
	      // initialDate: '2023-01-12',
	      initialDate: dateString,
	      navLinks: true, // can click day/week names to navigate views
	      selectable: true,
	      selectMirror: true,
	      select: function(arg) {
	        var title = prompt('Event Title:');
	        if (title) {
	          calendar.addEvent({
	            title: title,
	            start: arg.start,
	            end: arg.end,
	            allDay: arg.allDay
	          })
	          console.log(arg.start);
	          console.log(arg.end);
	          var startD = arg.start;
	          var endD = arg.end;
	          
	          var inputS = startD.getFullYear() + '-' + ('0' + (startD.getMonth() + 1)).slice(-2) + '-' + ('0' + startD.getDate()).slice(-2);
	          var inputE = endD.getFullYear() + '-' + ('0' + (endD.getMonth() + 1)).slice(-2) + '-' + ('0' + endD.getDate()).slice(-2);
	          eventAdd({title, inputS, inputE});
	        }
	        calendar.unselect()
	      },
	      eventClick: function(arg) {
	        if (confirm('Are you sure you want to delete this event?')) {
	          arg.event.remove()
	          var titleD = arg.event.title;
	          var startDD = arg.event.start;
              var endDD = arg.event.end;
              
              var inputDS = startDD.getFullYear() + '-' + ('0' + (startDD.getMonth() + 1)).slice(-2) + '-' + ('0' + startDD.getDate()).slice(-2);
              var inputDE = endDD.getFullYear() + '-' + ('0' + (endDD.getMonth() + 1)).slice(-2) + '-' + ('0' + endDD.getDate()).slice(-2);
              
              eventDel({titleD, inputDS, inputDE});
	        }
	      },
	      editable: true,
	      dayMaxEvents: true, // allow "more" link when too many events
	      events: events
	    });

	    calendar.render();
    }
    
    function eventAdd(info = { title, inputS, inputE }) {
        console.log(info);
        fetch('addEvent.do?title=' + info.title + '&start=' + info.inputS + '&end=' + info.inputE)
        .then(result => result.json())
        .then(result => console.log(result))
        .catch(err => console.error(err));
    }
    
    function eventDel(info = { titleD, inputDS, inputDE }) {
        console.log(info);
        fetch('removeEvent.do?title=' + info.titleD + '&start=' + info.inputDS + '&end=' + info.inputDE)
        .then(result => result.json())
        .then(result => console.log(result))
        .catch(err => console.error(err));
    }
  });

</script>

  <script src='./js/index.global.js'></script>