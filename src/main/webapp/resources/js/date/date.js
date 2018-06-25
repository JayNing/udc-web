$(function() {
    //日期
    var nowTemp = new Date();
    var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
    var checkin = $('#start-time').fdatepicker({
        onRender: function (date) {
            return date.valueOf() < now.valueOf() ? '' : '';
        },
        format: 'yyyy-mm-dd'
    }).on('changeDate', function (ev) {
        if (ev.date.valueOf() > checkout.date.valueOf()) {
            var newDate = new Date(ev.date);
            newDate.setDate(newDate.getDate() + 1);
            checkout.update(newDate);
        }
        checkin.hide();
        $('#end-time')[0].focus();
    }).data('datepicker');
    var checkout = $('#end-time').fdatepicker({
        onRender: function (date) {
            return date.valueOf() < checkin.date.valueOf() ? 'disabled' : '';
        },
        format: 'yyyy-mm-dd'
    }).on('changeDate', function (ev) {
        checkout.hide();
    }).data('datepicker');
});