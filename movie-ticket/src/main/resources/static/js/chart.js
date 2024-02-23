const labels = ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'];
let int = [];

function fetchData() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: 'http://localhost:8080/api/data',
            method: 'GET',
            data: 'json',
            success: function (data) {
                console.log(data);
                resolve(data);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

// Lấy dữ liệu từ yêu cầu Ajax
fetchData()
    .then(data => {
        for (let i = 0; i < data.length; i++) {
            int.push(data[i]);
            console.log(int[i]);
        }
        processData();
    })
    .catch(error => {
        console.error('Lỗi khi lấy dữ liệu:', error);
    });

function processData() {
    const data = {
        labels: labels,
        datasets: [
            {
                label: 'Doanh thu(/Tháng)',
                backgroundColor: 'blue',
                borderColor: 'blue',
                data: [int[0], int[1], int[2], int[3], int[4], int[5], int[6], int[7], int[8], int[9], int[10], int[11]],
                tension: 0.4
            },
            {
                label: 'Biến động doanh thu',
                backgroundColor: 'green',
                borderColor: 'green',
                data: [int[0],int[1]-int[0], int[2]-int[1], int[3]-int[2], int[4]-int[3], int[5]-int[4], int[6]-int[5],int[7]-int[6],int[8]-int[7],int[9]-int[8],int[10]-int[9],int[11]-int[10]],
                tension: 0.4
            },
        ],
    };

    const config = {
        type: 'line',
        data: data,
    };

    const canvas = document.getElementById('canvas');
    const chart = new Chart(canvas, config);
}
