async function sendData() {
    // Obtener los valores del formulario
    const filter = document.getElementById("filter").value;
    const keyword = document.getElementById("keyword").value;
    const page = document.getElementById("page").value;
    const size = document.getElementById("size").value;

    // Construir el cuerpo de la solicitud POST
    const requestBody = {
        filter: filter,
        keyword: keyword,
        page: page,
        size: size
    };

    try {
        // Realizar la solicitud Fetch al servidor con método POST
        const response = await fetch('/movieSearch', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        });

        // Verificar el estado de la respuesta
        if (!response.ok) {
            throw new Error(`Error: ${response.status} - ${response.statusText}`);
        }

        // Obtener los datos de la respuesta JSON
        const data = await response.json();

        // Llamar a la función para actualizar la tabla con los datos recibidos
        updateTable(data);
    } catch (error) {
        console.error('Error:', error);
    }
}

/*
function updateTable(data) {
    const table = document.getElementById("resultTable");
    table.innerHTML = ""; // Limpiar la tabla antes de actualizar

    // Crear encabezados de la tabla
    const headers = Object.keys(data[0]);
    const headerRow = table.insertRow();
    headers.forEach(header => {
        const th = document.createElement("th");
        th.textContent = header;
        headerRow.appendChild(th);
    });

    // Llenar la tabla con los datos
    data.forEach(item => {
        const row = table.insertRow();
        headers.forEach(header => {
            const cell = row.insertCell();
            cell.textContent = item[header];
        });
    });
}
*/

function updateTable(data) {
    const table = document.getElementById("resultTable");
    table.innerHTML = ""; // Limpiar la tabla antes de actualizar

    // Crear encabezados de la tabla, incluyendo uno adicional para el botón "View"
    const headers = Object.keys(data[0]);
    headers.push("View");  // Agregar una columna adicional para el botón "View"
    const headerRow = table.insertRow();
    headers.forEach(header => {
        const th = document.createElement("th");
        th.textContent = header;
        headerRow.appendChild(th);
    });

    // Llenar la tabla con los datos, incluyendo un botón "View" en cada fila
    data.forEach(item => {
        const row = table.insertRow();
        headers.forEach(header => {
            const cell = row.insertCell();
            
            if (header === "View") {
                // Crear el botón "View" y asignarle un evento
                const viewButton = document.createElement("button");
                viewButton.textContent = "View";
                viewButton.addEventListener("click", function() {
                    // Aquí puedes agregar la lógica para manejar el clic en el botón "View"
                    // Puedes acceder a los datos de la fila actual a través de la variable 'item'
                    
                    console.log("View button clicked for row:", item);
                    let viewId = item.movieId;
                    console.log("View id :" + viewId);
                    viewInfoMovie(viewId);
                });
                cell.appendChild(viewButton);
            } else {
                // Llenar las celdas con los datos del objeto 'item'
                cell.textContent = item[header];
            }
        });
    });
}

async function viewInfoMovie(viewId) {
    // Construir el cuerpo de la solicitud POST
    const requestBody = {
        movieId:viewId
    };

    try {
        // Realizar la solicitud Fetch al servidor con método POST
        const response = await fetch('/movieSearch/infoMovie', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        });

        // Verificar el estado de la respuesta
        if (!response.ok) {
            throw new Error(`Error: ${response.status} - ${response.statusText}`);
        }
        // Obtener los datos de la respuesta JSON
        const data = await response.json();
        showMovieModal(data);
        console.log("Dades que aniran a el modal " + data);
        
    } catch (error) {
        console.error('Error:', error);
    }
}

function showMovieModal(movieData) {
    // Llenar el contenido del modal con la información de la película
    const modalBody = document.getElementById("movieModalBody");
    modalBody.innerHTML = "";

    for (const [key, value] of Object.entries(movieData)) {
        const row = document.createElement("div");
        row.innerHTML = `<strong>${key}:</strong> ${value}`;
        modalBody.appendChild(row);
    }

    // Mostrar el modal
    $('#movieModal').modal('show');
}



const keywordInput = document.getElementById("keyword");
keywordInput.addEventListener("input" , function() {
    //Envia les noves dades a sendData perque faci la peticio
    if(keywordInput.value.trim() !=='') {
        sendData();
    }
})

//proba 

// Event Listener for Page input
document.getElementById('page').addEventListener('change', function() {
    sendData();
});

// Event Listener for Size input
document.getElementById('size').addEventListener('change', function() {
    sendData();
});



/*

ORIGINAL I FUNCIONAL
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


//Per ara no esta actiu
        // Funció que navega a una página específica
/*
function goToPage() {
    var pageNumberInput = document.getElementById('pageNumberInput').value;
    var totalPages = ${totalPages};

    // Validar que el valor sea un número entre 1 y totalPages
    if (/^[1-9]\d*$/.test(pageNumberInput)) {
        var page = parseInt(pageNumberInput, 10) - 1;

        // Verificar que la página esté dentro del rango permitido
        if (page >= 0 && page < totalPages) {
            window.location.href = '/movieSearch?page=' + page;
        }
    }
}
*/