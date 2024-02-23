tinymce.init({
    selector: 'textarea',
    plugins: 'advlist autolink lists link image charmap print preview anchor searchreplace visualblocks code fullscreen insertdatetime media table paste code help wordcount',
    toolbar: 'undo redo | formatselect | bold italic underline strikethrough | alignleft aligncenter alignright alignjustify | outdent indent | numlist bullist | link image media | removeformat | code',
    image_title: true,
    automatic_uploads: true,
    file_picker_types: 'image',
    file_picker_callback: (cb, value, meta) => {
        const input = document.createElement('input');
        input.setAttribute('type', 'file');
        input.setAttribute('accept', 'image/*');

        input.addEventListener('change', (e) => {
            const file = e.target.files[0];

            const reader = new FileReader();
            reader.addEventListener('load', () => {
                const formData = new FormData();
                formData.append('file', file);
                debugger;
                fetch('/upload/image', {
                    method: 'POST',
                    body: formData
                })
                    .then(response => {
                        if (response.ok) {
                            return response.json();
                        } else {
                            throw new Error('Lỗi khi tải lên file ảnh');
                        }
                    })
                    .then(data => {
                        const imageUrl = data.url;
                        const id = 'blobid' + (new Date()).getTime();
                        const blobCache = tinymce.activeEditor.editorUpload.blobCache;
                        const blobInfo = blobCache.create(id, file, imageUrl);
                        blobCache.add(blobInfo);
                        cb(imageUrl, {title: file.name});
                    })
                    .catch(error => {
                        // Xử lý lỗi tải lên
                        console.error(error);
                    });
            });
            reader.readAsDataURL(file);
        });
        input.click();
    },
});