
const moment = require("moment");

module.exports = _ => ({
  formatDisplay(d) {
    // return d;
    console.log(d)
    return moment(d).format("MMMM DD")
  },
  formatMonth(d) {
    return moment(d).format("MMMM")
    // return moment(d).toString()
  },
  getWeekDayArray() {
    return ["S", "T", "Q", "Q", "S", "S", "D"];
  }
});