    window.addEventListener('DOMContentLoaded', () => {
    const tableBody = document.getElementById('apartmentTableBody');

    fetch('http://localhost:8080/api/apartmentList')
        .then(response => response.json())
        .then(data => {
            console.log(data)
            if (data.code === 200) {
                data.data.forEach(apartment => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${apartment.id}</td>
                        <td>${apartment.address}</td>
                        <td>${apartment.rentalPrice}</td>
                        <td>${apartment.numberOfRoom}</td>
                    `;
                    tableBody.appendChild(row);
                });
            } else {
                console.error('Failed to fetch data from the API');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});