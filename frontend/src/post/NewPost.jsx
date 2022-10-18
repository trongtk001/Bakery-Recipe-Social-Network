import React, { useEffect, useState } from "react";
import { Link, useHistory } from "react-router-dom";
import { useDispatch } from "react-redux";
import { FaTrashAlt } from "react-icons/fa";
import { Post } from "../store/actions/post.action";
import { storeImageToFireBase } from "../utils/storeImageToFirebase.";
import Load from "./../components/load/index";
import { useIsLogin } from "../hooks/useIsLogin";
import { NotificationContainer } from "react-notifications";
import { useIsHidden } from "../hooks/useIsHidden";

function NewPost() {
  const dispatch = useDispatch();
  const history = useHistory();
  const { loading } = useIsLogin();
  const { hidden, handleClick } = useIsHidden();
  const [postBody, setPostBody] = useState("");
  const [postImages, setPostImages] = useState([]);
  const [postVideos, setPostVideos] = useState([]);
  console.log(
    "🚀 ~ file: NewPost.jsx ~ line 20 ~ NewPost ~ setPostVideos",
    setPostVideos
  );
  const [recipe, setRecipe] = useState({
    name: "",
    description: "",
    steps: "",
    tool: "",
  });
  const [selectedFile, setSelectedFile] = useState();
  const [isLoading, setIsLoading] = useState(false);
  useEffect(
    () => {
      const uploadImage = async () => {
        setIsLoading(true);
        if (!selectedFile) {
          setIsLoading(false);
          return;
        }
        const { isSuccess, imageUrl, message } = await storeImageToFireBase(
          selectedFile
        );
        if (isSuccess) {
          setPostImages([...postImages, { image: imageUrl }]);
          setIsLoading(false);
          return imageUrl;
        } else {
          console.log(message);
        }
        setIsLoading(false);
      };
      uploadImage();
    },
    // eslint-disable-next-line
    [selectedFile]
  );
  const onSelectFile = (e) => {
    if (!e.target.files || e.target.files.length === 0) {
      setSelectedFile(undefined);
      return;
    }
    setSelectedFile(e.target.files[0]);
  };

  const handleChange = (event) => {
    const { value, name } = event.target;
    setRecipe({
      ...recipe,
      [name]: value,
    });
  };
  const handleChangeBody = (event) => {
    setPostBody(event.target.value);
  };
  const deleteImage = (id) => {
    setPostImages(postImages.filter((elm, index) => index !== id));
  };
  const clickSubmit = (event) => {
    event.preventDefault();
    dispatch(Post(postBody, postImages, postVideos, recipe, history));
  };

  return (
    <div className="container m-auto">
      <h1 className="text-2xl leading-none text-gray-900 tracking-tight mt-3">
        Create A New Post
      </h1>
      <form onSubmit={clickSubmit} noValidate>
        <div className="grid lg:grid-cols-3 mt-12 gap-8">
          <div>
            <div className="px-4 py-3 -mx-5 -mt-4 mb-5 border-b">
              <h4>Profile Photo</h4>
            </div>
            <div className="flex-center text-center dark:text-gray-300">
              <div className="flex flex-col choose-upload text-center">
                {postImages.length > 0 ? (
                  postImages.map((postImages, index) => (
                    <div
                      className="bg-gray-400 border-2 border-dashed flex flex-col h-56 items-center justify-center relative w-full rounded-lg dark:bg-gray-800 dark:border-gray-600"
                      style={{
                        backgroundImage: `url(${postImages.image})`,
                      }}
                      key={index}
                    >
                      <Link
                        to="#"
                        className="hover:text-red-400 text-red-500"
                        style={{
                          position: "absolute",
                          right: "10px",
                          top: "10px",
                        }}
                        onClick={() => deleteImage(index)}
                      >
                        <FaTrashAlt />
                      </Link>
                    </div>
                  ))
                ) : (
                  <div className="bg-gray-400 border-2 border-dashed flex flex-col h-56 items-center justify-center relative w-full rounded-lg dark:bg-gray-800 dark:border-gray-600">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 20 20"
                      fill="currentColor"
                      className="w-12"
                    >
                      <path d="M5.5 13a3.5 3.5 0 01-.369-6.98 4 4 0 117.753-1.977A4.5 4.5 0 1113.5 13H11V9.413l1.293 1.293a1 1 0 001.414-1.414l-3-3a1 1 0 00-1.414 0l-3 3a1 1 0 001.414 1.414L9 9.414V13H5.5z" />
                      <path d="M9 13h2v5a1 1 0 11-2 0v-5z" />
                    </svg>
                  </div>
                )}
                <p className="my-3 leading-6">
                  Do you have a photo wants to share us <br /> please upload her
                  ..
                </p>
                <div uk-form-custom className="uk-form-custom">
                  {isLoading ? (
                    <>
                      <button
                        type="button"
                        disabled
                        style={{
                          opacity: ".4",
                          display: "inline-flex",
                          alignItems: "center",
                          height: "40px",
                        }}
                        className="button soft-warning small"
                      >
                        Choose file <Load isSmall={true} />
                      </button>
                    </>
                  ) : (
                    <>
                      <input
                        onChange={onSelectFile}
                        type="file"
                        accept="image/*"
                        className="shadow-none bg-gray-100"
                      />
                      <button
                        type="button"
                        className="button soft-warning small"
                      >
                        Choose file
                      </button>
                    </>
                  )}
                </div>
              </div>
            </div>
            <div className="px-4 py-3 -mx-5 -mb-4 mt-5 border-t text-sm dark:border-gray-500 dark:text-gray-500">
              Your Photo size Must be Maxmium 999MB
            </div>
          </div>
          <div className="bg-white dark:bg-gray-900 rounded-md lg:shadow-lg shadow col-span-2">
            <div className="grid grid-cols-2 gap-3 lg:p-6 p-4">
              <div className="col-span-2">
                <label htmlFor> postBody</label>
                <input
                  type="text"
                  name="postBody"
                  placeholder="Your postBody.."
                  className="shadow-none bg-gray-100"
                  onChange={handleChangeBody}
                  value={postBody}
                />
              </div>
              {hidden && (
                <>
                  <div>
                    <label htmlFor> name</label>
                    <input
                      type="text"
                      name="name"
                      placeholder="name post.."
                      className="shadow-none bg-gray-100"
                      onChange={handleChange}
                      value={recipe.name}
                    />
                  </div>
                  <div>
                    <label htmlFor> steps</label>
                    <input
                      type="text"
                      name="steps"
                      placeholder="Your steps.."
                      className="shadow-none bg-gray-100"
                      onChange={handleChange}
                      value={recipe.steps}
                    />
                  </div>
                  <div>
                    <label htmlFor> tool</label>
                    <input
                      type="text"
                      name="tool"
                      placeholder="Your tool.."
                      className="shadow-none bg-gray-100"
                      onChange={handleChange}
                      value={recipe.tool}
                    />
                  </div>
                  <div className="col-span-2">
                    <label htmlFor="about">Content Post</label>
                    <textarea
                      id="body"
                      name="description"
                      className="shadow-none bg-gray-100"
                      placeholder="Your description.."
                      onChange={handleChange}
                      value={recipe.description}
                    />
                  </div>
                </>
              )}
            </div>
            <div className="bg-gray-10 p-6 pt-0 flex justify-end space-x-3">
              {hidden ? (
                <Link
                  to="#"
                  className="button bg-blue-700"
                  onClick={handleClick}
                >
                  Cancel recipe
                </Link>
              ) : (
                <Link
                  to="#"
                  className="button bg-blue-700"
                  onClick={handleClick}
                >
                  Create recipe
                </Link>
              )}
              <Link to="#" className="p-2 px-4 rounded bg-gray-50 text-red-500">
                Cancel
              </Link>
              {loading ? (
                <button
                  className="button bg-blue-700"
                  disabled
                  style={{
                    opacity: ".4",
                    display: "inline-flex",
                    alignItems: "center",
                    height: "40px",
                  }}
                >
                  Create Post
                  <Load isSmall={true} />
                </button>
              ) : (
                <button className="button bg-blue-700">Create Post</button>
              )}
            </div>
          </div>
        </div>
      </form>
      <NotificationContainer />
    </div>
  );
}

export default NewPost;
