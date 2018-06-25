//初始化分页
function getRange(curr, all, count) {
    //计算显示的页数
    curr = parseInt(curr);
    all = parseInt(all);
    count = parseInt(count);
    var from = curr - parseInt(count / 2);
    var to = curr + parseInt(count / 2) + (count % 2) - 1;
    //显示的页数容处理
    if (from <= 0) {
        from = 1;
        to = from + count - 1;
        if (to > all) {
            to = all;
        }
    }
    if (to > all) {
        to = all;
        from = to - count + 1;
        if (from <= 0) {
            from = 1;
        }
    }
    var range = [];
    for (var i = from; i <= to; i++) {
        range.push(i);
    }
    if(all>0){
        range.push('下一页','尾页');
        range.unshift('首页','上一页');
    }
    return range;
}

