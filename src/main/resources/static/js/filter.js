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



let viewId = null;
/*
function updateTable(data) {
    const table = document.getElementById("resultTable");
    table.innerHTML = "";

    const headers = Object.keys(data[0]);
    headers.push("View", "Actors");
    const headerRow = table.insertRow();

    headers.forEach(header => {
        const th = document.createElement("th");
        th.textContent = header;
        headerRow.appendChild(th);
    });

    data.forEach(item => {
        const row = table.insertRow();

        headers.forEach(header => {
            const cell = row.insertCell();

            if (header === "View") {
                const viewButton = document.createElement("button");
                viewButton.textContent = "View";
                viewButton.addEventListener("click", function () {
                    viewId = item.movieId;
                    viewInfoMovie(viewId);
                });
                cell.appendChild(viewButton);
            } else if (header === "Actors") {
                // Verificar si el usuario ha iniciado sesión antes de mostrar el botón "Actors"
                if (isEmailAvailable) {
                    const actorsButton = document.createElement("button");
                    actorsButton.textContent = "Actors";
                    actorsButton.addEventListener("click", function () {
                        viewId = item.movieId;
                        viewActors(viewId);
                    });
                    cell.appendChild(actorsButton);
                } else {
                    console.log("Invalid session")
                    // No mostrar el botón "Actors" si el usuario no ha iniciado sesión
                    cell.textContent = "Login required";
                }
            } else {
                cell.textContent = item[header];
            }
        });
    });
}*/

function updateTable(data) {
    const table = document.getElementById("resultTable");
    table.innerHTML = ""; // Limpiar la tabla antes de actualizar

    // Crear encabezados de la tabla, incluyendo botones adicionales
    const headers = Object.keys(data[0]);
    if (isEmailAvailable) {
        headers.push("View", "Actors", "AddToMovie");  // Agregar columnas adicionales para los botones
    } else {
        headers.push("View", "Actors");
    }
    const headerRow = table.insertRow();
    headers.forEach(header => {
        const th = document.createElement("th");
        th.textContent = header;
        headerRow.appendChild(th);
    });

    // Llenar la tabla con los datos, incluyendo botones en cada fila
    data.forEach(item => {
        const row = table.insertRow();
        headers.forEach(header => {
            const cell = row.insertCell();

            if (header === "View") {
                // Crear el botón "View" y asignarle un evento
                const viewButton = document.createElement("button");
                viewButton.textContent = "View";
                viewButton.addEventListener("click", function () {
                    viewId = item.movieId;
                    viewInfoMovie(viewId);
                });
                cell.appendChild(viewButton);
            } else if (header === "Actors") {
                // Crear el botón "Actors" y asignarle un evento
                const actorsButton = document.createElement("button");
                actorsButton.textContent = "Actors";
                actorsButton.addEventListener("click", function () {
                    viewId = item.movieId;
                    viewActors(viewId);
                });
                cell.appendChild(actorsButton);


            } else if (header === "AddToMovie") {
                // Crear el botón "Actors" y asignarle un evento
                const modifyButton = document.createElement("button");
                modifyButton.textContent = "Relations";
                modifyButton.addEventListener("click", function () {
                    viewId = item.movieId;
                    //viewActors(viewId);
                    modifyModal(viewId);
                });
                cell.appendChild(modifyButton);

            } else {
                // Llenar las celdas con los datos del objeto 'item'
                cell.textContent = item[header];
            }
        });
    });
}

async function modifyModal(viewId) {
    var addToMovieSelect = document.getElementById("addToMovieSelect").value;
    console.log("Valor seleccionat " + addToMovieSelect);

    let relationSelect = addToMovieSelect.substring(0, 1).toUpperCase() + addToMovieSelect.substring(1);
    const requestBody = {
        movieId: viewId
    };

    try {
        // Realizar la solicitud Fetch al servidor con método POST
        const response = await fetch(`/movieSearch/movie${relationSelect}`, {
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
        //showActorsModal(data, viewId);

    } catch (error) {
        console.error('Error:', error);
    }
}



async function viewActors(viewId) {
    // Construir el cuerpo de la solicitud POST
    const requestBody = {
        movieId: viewId

    };

    try {
        // Realizar la solicitud Fetch al servidor con método POST
        const response = await fetch('/movieSearch/movieActor', {
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
        showActorsModal(data, viewId);
    } catch (error) {
        console.error('Error:', error);
    }
}


async function viewInfoMovie(viewId) {
    // Construir el cuerpo de la solicitud POST
    const requestBody = {
        movieId: viewId
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
    // Rellena el modal amb la informacio
    const modalBody = document.getElementById("movieModalBody");
    modalBody.innerHTML = "";

    for (const [key, value] of Object.entries(movieData)) {
        const row = document.createElement("div");

        if (key === "director") {
            const directorList = document.createElement("ul");

            // Iterar a través de cada director en la lista
            value.forEach(director => {
                const directorItem = document.createElement("li");
                row.innerHTML = `<strong>${key}:</strong>`;
                directorItem.textContent = `${director.personName}`;
                directorList.appendChild(directorItem);
            });

            row.appendChild(directorList);

        } else if (key === "genres") {
            const genresList = document.createElement("ul");

            // Iterar a través de cada director en la lista
            value.forEach(genres => {
                const genresItem = document.createElement("li");
                row.innerHTML = `<strong>${key}:</strong>`;
                genresItem.textContent = `${genres.genreName}`;
                genresList.appendChild(genresItem);
            });

            row.appendChild(genresList);
        } else {
            row.innerHTML = `<strong>${key}:</strong> ${value}`;
        }
        modalBody.appendChild(row);
    }

    // Mostrar el modal
    $('#movieModal').modal('show');
}

function showActorsModal(actorsData, viewId) {
    console.log("Actors data ");
    console.log(actorsData);

    // Rellena el modal con la información de los actores
    const modalBody = document.getElementById("movieModalBody");

    modalBody.innerHTML = "";

    // Crear una tabla para mostrar la información de los actores
    const table = document.createElement("table");
    table.classList.add("table");
    table.innerHTML = `
        <thead>
            <tr>
                <th>Person Name</th>
                <th>Gender</th>
                <th>Character Name</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    `;

    // Llenar la tabla con los datos de los actores
    const tbody = table.querySelector("tbody");
    actorsData.forEach(actor => {
        const row = tbody.insertRow();
        const cellPersonName = row.insertCell(0);
        const cellGender = row.insertCell(1);
        const cellCharacterName = row.insertCell(2);
        const cellAction = row.insertCell(3);
        // const cellId = row.insertCell(4);

        cellPersonName.textContent = actor.personName;
        cellGender.textContent = actor.gender;
        cellCharacterName.textContent = actor.characterName;
        // cellId.textContent = actor.personId;
        [cellPersonName, cellGender, cellCharacterName].forEach(cell => {
            cell.addEventListener("dblclick", async function () {
                console.log("cel");
                console.log(cell);

                if (cell === cellGender) {
                    // Si se hace doble clic en la celda de género, llamar a showDynamicPrompt()
                    const selectedGender = await showDynamicPrompt();

                    // Verificar si se seleccionó un género antes de actualizar el contenido de la celda
                    if (selectedGender !== null && selectedGender !== undefined) {
                        cell.textContent = selectedGender;
                        // También puedes actualizar el objeto actor con el nuevo género si es necesario
                        actor.gender = selectedGender;
                        console.log("selectedGender");
                        console.log(selectedGender);
                        console.log("ACTOR");
                        console.log(actor);
                        editCellContent(cell, actor, viewId);
                    }
                } else {
                    // Si se hace doble clic en otras celdas, llamar a la función editCellContent()
                    editCellContent(cell, actor, viewId);
                }
            });
        });
        if (isEmailAvailable) {

            // Agregar el botón en la columna "Action"
            const actionButton = document.createElement("button");
            actionButton.textContent = "X";
            actionButton.addEventListener("click", function () {
                // Enviar la información al servidor al hacer clic en el botón
                sendDeleteRelation(viewId, actor.personId);

            });
            cellAction.appendChild(actionButton);
        }

    });

    modalBody.appendChild(table);

    // Mostrar el modal
    $('#movieModal').modal('show');
}




function editCellContent(cell, actor, viewId) {
    const originalContent = cell.textContent;
    const inputElement = document.createElement("input");
    inputElement.value = originalContent;

    inputElement.addEventListener("blur", function () {
        // Actualizar el contenido y enviar al servidor
        const updatedContent = inputElement.value;
        cell.textContent = updatedContent;

        // Aquí puedes enviar la información actualizada al servidor
        //sendUpdatedInfo(actor.personId, cell.textContent);
        sendUpdatedInfo(actor, viewId);
    });

    cell.textContent = "";
    cell.appendChild(inputElement);
    inputElement.focus();
}
function sendUpdatedInfo(person, viewId) {
    /* const data = {
        personId: personId,
        updatedContent: updatedContent
    };*/
    const data = {
        personId: person.personId,
        movieId: viewId,
        characterName: person.characterName,
        genderId: person.gender.genderId
    };

    /*  const requestBody = {
                personId: personId,
                movieId: viewid,
                characterName: characterName,
                genderId: selectedGender.genderId
            }
*/

    console.log("Update movie Cast ");
    console.log(data);


    fetch('/adminArea/updateMoviCast', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            // Manejar la respuesta del servidor (puede ser un mensaje de éxito, etc.)
            alert(data);
        })
        .catch(error => {
            // Manejar errores en la solicitud
            console.error('Error:', error);
        });
}

/*
//Envia la info per actualitzar els camps
function sendUpdatedInfo(personId, updatedContent) {
    const data = {
        personId: personId,
        updatedContent: updatedContent
    };


    console.log("Update movie Cast " + data);


    fetch('/adminArea/updateMoviCast', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        // Manejar la respuesta del servidor (puede ser un mensaje de éxito, etc.)
        alert(data);
    })
    .catch(error => {
        // Manejar errores en la solicitud
        console.error('Error:', error);
    });
}
*/


// Función para enviar la selección de actor al servidor
async function sendDeleteRelation(movieId, actorId) {
    const requestBody = {
        movieId: movieId,
        actorId: actorId
    };
    const userConfirmed = window.confirm(`Are you sure you want to decast this actor in Movie by id :  ` + movieId + ' ?');
    if (userConfirmed) {
        try {
            const response = await fetch('/adminArea/decastActor', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestBody)
            }).then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud: ' + response.statusText);
                }
                return response.text();
            })
                .then(responseData => {

                    // Puedes manejar la respuesta del servidor si es necesario
                    console.log('Actor selection successful');
                    //recarrega la pagina

                    $('#movieModal').modal('hide');
                    viewActors(movieId);
                    alert(responseData);
                })
        } catch (error) {
            console.error('Error:', error);
        }
    }
}




/*
async function applyFilter() {
    // Obtener el valor seleccionado del select
    const filterType = document.getElementById("filterType").value;

    // Realizar la acción deseada según el tipo de filtro seleccionado
    console.log("Selected Filter Type:", filterType);
    let url = filterType.substring(0, 1).toUpperCase() + filterType.substring(1);

    // Construir el cuerpo de la solicitud POST
    const requestBody = {
        filterType: filterType,
        movieId:viewId
        //pasar movieId
        
        // Agrega otros campos según sea necesario
    };

    // Realizar la solicitud Fetch al servidor
    fetch(`/movieSearch/movie${url}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    })
    .then(response => {
        console.log(response);
        if (!response.ok) {
            throw new Error(`Error: ${response.status} - ${response.statusText}`);
        }
        return response.json();
    })
    .then(data => {
        
        // Visualizar la lista devuelta por el servidor
        const resultContainer = document.getElementById("filterResult");
        resultContainer.innerHTML = ""; // Limpiar el contenedor antes de actualizar

        // Crear lista o actualizar según el formato de los datos recibidos
        // Ejemplo: Crear una lista de elementos <li> para cada elemento recibido
        const list = document.createElement("ul");
        data.forEach(item => {
            const listItem = document.createElement("li");
            listItem.textContent = item;
            list.appendChild(listItem);
        });
        
        resultContainer.appendChild(list);
        
        
        // Mostrar los resultados filtrados en el modal
        updateFilterResult(data);
    })
    .catch(error => {
        console.error('Error:', error);
    });


    // Cerrar el modal
    $('#filterModal').modal('hide');
}
*/






// Añade un evento al botón "Add new person to cast" para abrir el modal de búsqueda de personas
const addPersonButton = document.getElementById('btnPersonSearch');
addPersonButton.addEventListener('click', function () {
    // Limpiar el campo de búsqueda y los resultados
    document.getElementById('personKeyword').value = '';
    document.getElementById('personResults').innerHTML = '';

    // Abrir el modal de búsqueda de personas
    $('#addPersonModal').modal('show');
});

// Función para realizar la búsqueda de personas
async function searchPersons() {
    const personKeyword = document.getElementById('personKeyword').value;
    const personPage = document.getElementById('personPage').value;
    const personSize = document.getElementById('personSize').value;

    // Construir el cuerpo de la solicitud POST
    const requestBody = {
        keyword: personKeyword,
        personPage: personPage,
        personSize: personSize
    };

    try {
        // Realizar la solicitud Fetch al servidor con método POST
        const response = await fetch('/movieSearch/person', {
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

        // Mostrar los resultados en el modal
        const personResults = document.getElementById('personResults');
        personResults.innerHTML = '';

        data.forEach(person => {
            const listItem = document.createElement('li');
            listItem.textContent = `${person.personName} (${person.personId})`;

            if (isEmailAvailable) {
                // Añade un botón para agregar la persona al elenco
                const addButton = document.createElement('button');
                addButton.textContent = 'Add';
                addButton.addEventListener('click', function () {
                    console.log("viewid person " + viewId)
                    addPersonToCast(person.personId, viewId);

                });
                listItem.appendChild(addButton);
            }
            personResults.appendChild(listItem);
        });
        // Llamar a la función para actualizar la tabla con los datos recibidos
        // updateTable(data);
    } catch (error) {
        console.error('Error:', error);
    }
}


// Función para agregar la persona seleccionada al elenco de la película
async function addPersonToCast(personId, viewid) {
    //El usuari ha de afegir el personatge que interpretara el actor
    const characterName = prompt("Enter the character name:");


    // Verificar si el usuario ingresó un valor
    if (characterName !== null && characterName.trim() !== "") {
        // Llamar a la función showDynamicPrompt para obtener el gender_id seleccionado
        const selectedGender = await showDynamicPrompt();
        console.log("Selected gender " + JSON.stringify(selectedGender));


        console.log(selectedGender.genderId)
        if (selectedGender) {
            const requestBody = {
                personId: personId,
                movieId: viewid,
                characterName: characterName,
                genderId: selectedGender.genderId
            }


            const userConfirmed = window.confirm(`Are you sure you want to add this actor in Movie by id :  ` + viewId + ' ?');
            if (userConfirmed) {
                try {
                    const response = await fetch('/adminArea/castPerson', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(requestBody)
                    }).then(response => {
                        if (!response.ok) {
                            throw new Error('Error en la solicitud: ' + response.statusText);
                        }
                        return response.text();
                    })
                        .then(responseData => {
                            alert(responseData)
                            //recarrega la pagina

                            $('#addPersonModal').modal('hide');
                            viewActors(viewId);
                            alert(responseData);
                        })
                } catch (error) {
                    console.error('Error:', error);
                }
            }
        } else {
            console.log("User canceled gender selection.");
        }
    } else {
        //Si l'usuari no ha introduit el personatge mostrar mensatge
        alert("Please enter a character name.");
    }

    // $('#addPersonModal').modal('hide');
}



//proba gender// Función para mostrar el modal con opciones dinámicas y devolver la opción seleccionada
async function showDynamicPrompt() {
    console.log("modal gender")
    return new Promise(async (resolve) => {
        // Realizar la solicitud Fetch al servidor para obtener las opciones de gender
        const response = await fetch('/adminArea/allGender', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
        });

        if (!response.ok) {
            console.error(`Error: ${response.status} - ${response.statusText}`);
            resolve(null); // Resolver con null en caso de error
            return;
        }
        const data = await response.json();

        // Limpiar la lista de opciones
        const optionsList = document.getElementById("genderList");
        optionsList.innerHTML = "";

        // Agregar cada opción a la lista
        data.forEach(option => {
            const listItem = document.createElement("li");
            listItem.textContent = option.gender;
            listItem.addEventListener("click", function () {
                // Cuando el usuario hace clic en una opción, cierra el modal y resuelve la promesa con la opción seleccionada
                hideDynamicPrompt();
                resolve(option);
            });
            optionsList.appendChild(listItem);
        });
        // Mostrar el modal
        const modal = document.getElementById("dynamicPromptModal");
        modal.style.display = "flex";
    });

}

// Obtener el botón de cerrar
const closeButton = document.querySelector("#dynamicPromptModal .close");
// Agregar un evento de clic al botón de cerrar
closeButton.addEventListener("click", function () {
    // Ocultar el modal al hacer clic en el botón de cerrar
    hideDynamicPrompt();
});

// Función para ocultar el modal
function hideDynamicPrompt() {
    const modal = document.getElementById("dynamicPromptModal");
    modal.style.display = "none";
}








function updateFilterResult(data) {
    const resultContainer = document.getElementById("filterResult");
    resultContainer.innerHTML = ""; // Limpiar el contenedor antes de actualizar

    // Crear lista o actualizar según el formato de los datos recibidos
    // Ejemplo: Crear una lista de elementos <li> para cada elemento recibido
    const list = document.createElement("ul");
    data.forEach(item => {
        const listItem = document.createElement("li");
        listItem.textContent = item;
        list.appendChild(listItem);
    });

    resultContainer.appendChild(list);
}




const keywordInput = document.getElementById("keyword");
keywordInput.addEventListener("input", function () {
    //Envia les noves dades a sendData perque faci la peticio
    if (keywordInput.value.trim() !== '') {
        sendData();
    }
})


// Event Listener for Page input
document.getElementById('page').addEventListener('change', function () {
    sendData();
});

// Event Listener for Size input
document.getElementById('size').addEventListener('change', function () {
    sendData();
});


//PERSON SEARCH
const keywordPersonInput = document.getElementById("personKeyword");
keywordPersonInput.addEventListener("input", function () {
    //Envia les noves dades a sendData perque faci la peticio
    if (keywordPersonInput.value.trim() !== '') {
        searchPersons();
    }
})


document.getElementById('personPage').addEventListener('change', function () {
    searchPersons();
});


document.getElementById('personSize').addEventListener('change', function () {
    searchPersons();
});