function selCity(val) {
      $.getJSON("https://api.openweathermap.org/data/2.5/weather?q=" + val + ",kr&appid=46b55a9f61cc588200575a3dda8e3069&units=metric",
        function (WeatherResult) {

          // 기온 출력
          $('.SeoulNowtemp').text('현재기온: ' + WeatherResult.main.temp + '°C');

          // 날씨 아이콘 출력
          var weathericonUrl =
            '<img src= "http://openweathermap.org/img/wn/'
            + WeatherResult.weather[0].icon +
            '.png" alt="' + WeatherResult.weather[0].description + '"/>'

          $('.SeoulIcon').html(weathericonUrl);
        });
    }

    // 오늘 날짜 출력
    $(document).ready(function () {
      function convertTime() {
        var now = new Date();
        var month = now.getMonth() + 1;
        var date = now.getDate();
        return month + '월' + date + '일';
      }

      var currentTime = convertTime();
      $('.nowtime').text(currentTime);
    });