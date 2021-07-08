const client = filestack.init(FILESTACK_API_KEY);

const uploadDone = (results) => {
    const fileData = results.filesUploaded[0];
    imageUrl.value = fileData.url;
    document.getElementById("filestack").innerHTML = "Change Image";
    if (['jpeg', 'png'].indexOf(fileData.mimetype.split('/')[1]) !== -1) {
    const container = document.getElementById('thumbnail-container');
    const thumbnail = document.getElementById('thumbnail') || new Image();
    thumbnail.id = 'thumbnail';
    thumbnail.src = client.transform(fileData.handle, {
      resize: {
        width: 200
      }
    });

    if (!container.contains(thumbnail)) {
      container.appendChild(thumbnail);
    }
  }
}

const uploadFailed = (results) => {
    return "There was a problem uploading your file.";
}

const filestackOptions = {
    onUploadDone: uploadDone,
    onFileUploadFailed: uploadFailed
};
const imageUrl = document.getElementById("image_url");

$("#filestack").click(() => {
    client.picker(filestackOptions).open();
})
