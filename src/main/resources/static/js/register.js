const client = filestack.init(filestackKey);

const uploadDone = (results) => {
    const fileData = results.filesUploaded[0];
    imageUrl.value = fileData.url;
    document.getElementById("filestack").innerHTML = "Change Image";
}

const uploadFailed = (results) => {
    return "There was a problem uploading your file.";
}

const options = {
    onUploadDone: uploadDone,
    onFileUploadFailed: uploadFailed
};
const imageUrl = document.getElementById("image_url");

$("#filestack").click(() => {
    client.picker(options).open();
})
