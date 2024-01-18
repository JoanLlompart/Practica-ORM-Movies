function sendData() {
    var form = document.getElementById('formFilter');
    var filter = document.getElementById('filter').value;
    var keyword = document.getElementById('keyword').value;
    
    /*
    var formData = new FormData();
    formData.append("filter",filter);
    formData.append("keyword",keyword);
    */
    var formData = {
    filter: filter,
    keyword: keyword
    }
    fetch('/movieSearch', {
        method: 'POST', 
        headers: {
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(formData)
    })
    .then(response => response.json())
    .then(data => {
        // Borrar la taula actual de dades per mostrar la nova
        var tableBody = document.querySelector('.table tbody');
        tableBody.innerHTML = "";
        
         // Llenar la tabla con los nuevos datos
        data.moviesFind.forEach(movie => {
            var newRow = tableBody.insertRow(tableBody.rows.length);
            newRow.insertCell(0).textContent = movie.movieId;
            newRow.insertCell(1).textContent = movie.title;
            newRow.insertCell(2).textContent = movie.releaseDate;
            newRow.insertCell(3).textContent = movie.voteAverage;
        });
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

/*
function sendData() {
    var form = document.getElementById('formFilter');
    var filter = document.getElementById('filter').value;
    var keyword = document.getElementById('keyword').value;
    var formData = {
        filter: filter,
        keyword: keyword
        }
        fetch('/movieSearch', {
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(formData)
        })
        .then(response => response.json())
        .then(data => {
            // Borrar la taula actual de dades per mostrar la nova
            var tableBody = document.querySelector('.table tbody');
            tableBody.innerHTML = "";
            
             // Llenar la tabla con los nuevos datos
            data.moviesFind.forEach(movie => {
                var newRow = tableBody.insertRow(tableBody.rows.length);
                newRow.insertCell(0).textContent = movie.movieId;
                newRow.insertCell(1).textContent = movie.title;
                newRow.insertCell(2).textContent = movie.releaseDate;
                newRow.insertCell(3).textContent = movie.voteAverage;
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }
*/