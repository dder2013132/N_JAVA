document.addEventListener('DOMContentLoaded', async function () {
  var calendarEl = document.getElementById('calendar');

  var today = new Date();

  var year = today.getFullYear();
  var month = ('0' + (today.getMonth() + 1)).slice(-2);
  var day = ('0' + today.getDate()).slice(-2);

  var dateString = year + '-' + month + '-' + day;

  var events = [];
  let result = await fetch('eventList.do');
  let result2 = await result.json();
  events = result2.data;

  // result2.forEach(item => events.push({ title: item.title, start: item.start, end: item.end }));

  // function eventListCallback(result) {
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
    select: async function (arg) {
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
        console.log(arg.allDay);
        var startD = arg.start;
        var endD = arg.end;

        var inputS = startD.getFullYear() + '-' + ('0' + (startD.getMonth() + 1)).slice(-2) + '-' + ('0' + startD.getDate()).slice(-2);
        var inputE = endD.getFullYear() + '-' + ('0' + (endD.getMonth() + 1)).slice(-2) + '-' + ('0' + endD.getDate()).slice(-2);
        await eventAdd({ title, inputS, inputE });
      }
      calendar.unselect()
    },
    eventClick: function (arg) {
      if (confirm('Are you sure you want to delete this event?')) {
        arg.event.remove()
        var titleD = arg.event.title;
        var startDD = arg.event.start;
        var endDD = arg.event.end;

        var inputDS = startDD.getFullYear() + '-' + ('0' + (startDD.getMonth() + 1)).slice(-2) + '-' + ('0' + startDD.getDate()).slice(-2);
        var inputDE = endDD.getFullYear() + '-' + ('0' + (endDD.getMonth() + 1)).slice(-2) + '-' + ('0' + endDD.getDate()).slice(-2);

        eventDel({ titleD, inputDS, inputDE });
      }
    },
    editable: true,
    dayMaxEvents: true, // allow "more" link when too many events
    events: events
  });

  calendar.render();
  // }

  async function eventAdd(info = { title, inputS, inputE }) {
    console.log(info);
    let r1 = await fetch('addEvent.do', {
      method: 'post',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
      body: 'title=' + info.title + '&start=' + info.inputS + '&end=' + info.inputE
    });

    console.log(r1.json());
    // fetch('addEvent.do?title=' + info.title + '&start=' + info.inputS + '&end=' + info.inputE)
    //   .then(result => result.json())
    //   .then(result => console.log(result))
    //   .catch(err => console.error(err));
  }

  function eventDel(info = { titleD, inputDS, inputDE }) {
    console.log(info);
    fetch('removeEvent.do?title=' + info.titleD + '&start=' + info.inputDS + '&end=' + info.inputDE)
      .then(result => result.json())
      .then(result => console.log(result))
      .catch(err => console.error(err));
  }
});