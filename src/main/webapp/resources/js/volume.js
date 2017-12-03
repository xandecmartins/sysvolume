function randColumns(){
        var result = "";
        var max = Math.floor(Math.random() * 11) + 1  ;
        for (var i = 0; i < max; i++) {
                var column = Math.floor(Math.random() * 30) + 1;
                result += column;
                result += ',';

        }
        document.getElementById('form:inptData').value = result.slice(0, -1);

}