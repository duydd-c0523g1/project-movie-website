import firebase from "./configulation.js";

const uploadedURLs = [];

async function handleUpload(e) {
    console.dir(e);
    // kết nối tới firebase storage với thư mục images
    const ref = firebase.storage().ref();
    const files = e.target.files;

    for (const file of files) {
        // đặt tên cho file để phân biệt với các file khác bằng cách thêm thời gian vào tên
        const name = +new Date() + "-" + file.name;
        const metadata = {
            contentType: file.type
        };

        try {
            const snapshot = await ref.child(name).put(file, metadata);
            const url = await snapshot.ref.getDownloadURL();
            console.log(url);
            uploadedURLs.push(url);
            if (uploadedURLs.length === files.length) {
                // upload image thành công lên firebase
                document.getElementById("image3").value = uploadedURLs[0];
            }
        } catch (error) {
            console.error(error);
        }
    }
}

document.getElementById("upload-banner").addEventListener("change", function (e) {
    handleUpload(e)
});