<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>메인페이지</title>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<link href='${cp}/css/main_nav.css' rel='stylesheet'/>
<link href='${cp}/css/main.css' rel='stylesheet' />
<link href='${cp}/css/fullcalendar.css' rel='stylesheet' />
<link href='${cp}/css/fullcalendar.print.css' rel='stylesheet'
	media='print' />

<script src='${cp}/js/jquery-1.10.2.js' type="text/javascript"></script>
<script src='${cp}/js/jquery-ui.custom.min.js' type="text/javascript"></script>
<script src='${cp}/js/fullcalendar.js' type="text/javascript"></script>
<script src='${cp}/js/main.js' type="text/javascript"></script>

<script class="cssdesk" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.0/moment.min.js" type="text/javascript"></script>
<script>
	
	$(document).ready(function() {
	    var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();

		/*  className colors

		className: default(transparent), important(red), chill(pink), success(green), info(blue)

		*/


		/* initialize the external events
		-----------------------------------------------------------------*/

		$('#external-events div.external-event').each(function() {

			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title: $.trim($(this).text()) // use the element's text as the event title
			};

			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);

			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex: 999,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});

		});


		/* initialize the calendar
		-----------------------------------------------------------------*/

		var calendar =  $('#calendar').fullCalendar({
			header: {
				left: 'title',
				center: 'agendaDay,agendaWeek,month',
				right: 'prev,next today'
			},
			editable: true,
			firstDay: 1, //  1(Monday) this can be changed to 0(Sunday) for the USA system
			selectable: true,
			defaultView: 'month',

			axisFormat: 'h:mm',
			columnFormat: {
                month: 'ddd',    // Mon
                week: 'ddd d', // Mon 7
                day: 'dddd M/d',  // Monday 9/7
                agendaDay: 'dddd d'
            },
            titleFormat: {
                month: 'MMMM yyyy', // September 2009
                week: "MMMM yyyy", // September 2009
                day: 'MMMM yyyy'                  // Tuesday, Sep 8, 2009
            },
			allDaySlot: false,
			selectHelper: true,
			select: function(start) {
				/* var title = prompt('이거 맞음:');
				if (title) { */
					var dt_start = moment(start).format('YYYY MM DD');
					location.href="${cp}/schedule/writesetview.sc.tc?start="+dt_start;
					/* /* calendar.fullCalendar('renderEvent',
						{
							title: title,
							start: start,
							end: end,
							allDay: allDay
						},
						true // make the event "stick"
					);
				}
				calendar.fullCalendar('unselect'); */
				
			},
			droppable: true, // this allows things to be dropped onto the calendar !!!
			drop: function(date, allDay) { // this function is called when something is dropped

				// retrieve the dropped element's stored Event Object
				var originalEventObject = $(this).data('eventObject');

				// we need to copy it, so that multiple events don't have a reference to the same object
				var copiedEventObject = $.extend({}, originalEventObject);

				// assign it the date that was reported
				copiedEventObject.start = date;
				copiedEventObject.allDay = allDay;

				// render the event on the calendar
				// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

				// is the "remove after drop" checkbox checked?
				if ($('#drop-remove').is(':checked')) {
					// if so, remove the element from the "Draggable Events" list
					$(this).remove();
				}

			},

			events: [
				{
					title: 'All Day Event',
					start: new Date(y, m, 1)
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: new Date(y, m, d-3, 16, 0),
					allDay: false,
					className: 'info'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: new Date(y, m, d+4, 16, 0),
					allDay: false,
					className: 'info'
				},
				{
					title: 'Meeting',
					start: new Date(y, m, d, 10, 30),
					allDay: false,
					className: 'important'
				},
				{
					title: 'Lunch',
					start: new Date(y, m, d, 12, 0),
					end: new Date(y, m, d, 14, 0),
					allDay: false,
					className: 'important'
				},
				{
					title: 'Birthday Party',
					start: new Date(y, m, d+1, 19, 0),
					end: new Date(y, m, d+1, 22, 30),
					allDay: false,
				},
				{
					title: 'Click for Google',
					start: new Date(y, m, 28),
					end: new Date(y, m, 29),
					url: 'http://google.com/',
					className: 'success'
				}
			],
		});


	});

</script>
<style>
#todo-list>div {
	width: 80%;
	margin: 0 auto;
	text-align: left;
	padding: 10px 0;
	border-bottom: 1px solid grey;
}

#todo-list>div>button {
	width: 20px;
	height: 20px;
	margin-left: 10px;
	border: 1px dotted gray;
	background-color: rgba(0, 0, 0, 0);
}

.background {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100vh;
	background-color: rgba(0, 0, 0, 0.3);
	z-index: 1000;
	/* 숨기기 */
	z-index: -1;
	opacity: 0;
}

.show {
	opacity: 1;
	z-index: 1000;
	transition: all 0.5s;
}

.window {
	position: relative;
	width: 100%;
	height: 100%;
}

.popup {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: #ffffff;
	box-shadow: 0 2px 7px rgba(0, 0, 0, 0.3);
	/* 임시 지정 */
	width: 300px;
	height: 400px;
	/* 초기에 약간 아래에 배치 */
	transform: translate(-50%, -40%);
}

.show .popup {
	transform: translate(-50%, -50%);
	transition: all 0.5s;
}

.goalset1{
	display:none;
}

.goalset2{
	display:none;
}
</style>
<body>
	<div id="wrap">
		<header>
            <div id="main_nav">
    </div>
    <script src='${cp}/js/main_nav.js' type="text/javascript"></script>
        </header>
		<div id="sub">
			<div id="cal_todo">
				<div id="calendar"></div>
				<div id="to">
					<h1>TO DO LIST</h1>
					<span></span><br> <input id="todo">
					<button id="add-button">+</button>
					<div id="todo-list"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script>
	
</script>
