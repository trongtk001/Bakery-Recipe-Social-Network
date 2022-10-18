import React, { useEffect, useState } from "react";
import { singlePost, remove, likePost, unlike } from "./apiPost";
import DefaultPost from "../images/defaultPost.png";
import { Link, useParams } from "react-router-dom";
import { isAuthenticated } from "../auth";
import Comment from "./Comment";
import DefaultProfile from "../images/avatar.png";

function SinglePost() {
  const { postId } = useParams();
  const [post, setPost] = useState("");
  const [like, setLike] = useState(false);
  const [likes, setLikes] = useState(0);
  const [comments, setComments] = useState([]);

  const checkLike = (likes) => {
    const userId = isAuthenticated() && isAuthenticated().user._id;
    let match = likes.indexOf(userId) !== -1;
    return match;
  };
  useEffect(
    () => {
      singlePost(postId).then((data) => {
        if (data.error) {
          console.log(data.error);
        } else {
          setPost(data);
          setLikes(data.likes.length);
          setLike(checkLike(data.likes));
          setComments(data.comments);
        }
      });
    },
    // eslint-disable-next-line
    [postId]
  );

  const updateComments = (comments) => {
    setComments(comments);
  };

  const likeToggle = () => {
    let callApi = like ? unlike : likePost;
    const userId = isAuthenticated().user._id;
    const token = isAuthenticated().token;
    callApi(userId, token, post._id).then((data) => {
      if (data.error) {
        console.log(data.error);
      } else {
        setLike(!likes);
        setLikes(data.likes.length);
      }
    });
  };

  const deletePost = () => {
    const postId = this.props.match.params.postId;
    const token = isAuthenticated().token;
    remove(postId, token).then((data) => {
      if (data.error) {
        console.log(data.error);
      } else {
        // this.setState({ redirectToHome: true });
        console.log(data.error);
      }
    });
  };

  const deleteConfirmed = () => {
    let answer = window.confirm("Are you sure you want to delete your post?");
    if (answer) {
      deletePost();
    }
  };

  return (
    <div className="container m-auto pt-5">
      <div className="bg-white py-3 px-4 shadow rounded-md dark:bg-gray-900 -mx-2 lg:mx-0">
        {!post ? (
          <div className="loadingio-spinner-rolling-l694aggo5r8">
            <div className="ldio-2jzvx09mle4">
              <div />
            </div>
          </div>
        ) : (
          <>
            <div className="px-5 py-4 flex uk-flex-between">
              <Link
                to={post.postedBy ? `/user/${post.postedBy._id}` : ""}
                className="flex items-center space-x-3"
              >
                <div className="w-10 h-10 rounded-full relative flex-shrink-0">
                  <img
                    src={`${process.env.REACT_APP_API_URL}/user/photo/${post.postedBy._id}`}
                    onError={(i) => (i.target.src = `${DefaultProfile}`)}
                    alt="avatar"
                    className="h-full rounded-full w-full"
                  />
                  <span className="absolute bg-green-500 border-2 border-white bottom-0 h-3 m-0.5 right-0 rounded-full shadow-md w-3" />
                </div>
                <div className="flex-1 min-w-0 relative text-gray-500">
                  <h4 className="font-semibold text-black text-lg">
                    {post.postedBy ? post.postedBy.name : " Unknown"}
                  </h4>
                  <p className="font-semibold leading-3 text-green-500 text-sm">
                    on {new Date(post.created).toDateString()}
                  </p>
                </div>
              </Link>
              {isAuthenticated().user &&
                isAuthenticated().user._id === post.postedBy._id && (
                  <div className="flex">
                    <Link to={`/post/edit/${post._id}`}>
                      <p className="flex hover:text-yellow-700 items-center leading-8 space-x-2 text-yellow-400 font-medium mr-2">
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          width="16"
                          height="16"
                          fill="currentColor"
                          class="bi bi-pencil-square"
                          viewBox="0 0 16 16"
                        >
                          <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                          <path
                            fill-rule="evenodd"
                            d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"
                          />
                        </svg>
                        <span className="lg:block hidden">Update Post</span>
                      </p>
                    </Link>
                    <Link to="#" onClick={deleteConfirmed}>
                      <p className="flex hover:text-red-400 items-center leading-8 space-x-2 text-red-500 font-medium">
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          width="16"
                          height="16"
                          fill="currentColor"
                          class="bi bi-trash-fill"
                          viewBox="0 0 16 16"
                        >
                          <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z" />
                        </svg>
                        <span className="lg:block hidden">Delete Post</span>
                      </p>
                    </Link>
                  </div>
                )}
              {isAuthenticated().user &&
                isAuthenticated().user.role === "admin" && (
                  <div class="card mt-5">
                    <div className="card-body">
                      <h5 className="card-title">Admin</h5>
                      <p className="mb-2 text-danger">
                        Edit/Delete as an Admin
                      </p>
                      <Link to={`/post/edit/${post._id}`}>
                        <p className="flex hover:text-yellow-700 items-center leading-8 space-x-2 text-yellow-400 font-medium mr-2">
                          <svg
                            xmlns="http://www.w3.org/2000/svg"
                            width="16"
                            height="16"
                            fill="currentColor"
                            class="bi bi-pencil-square"
                            viewBox="0 0 16 16"
                          >
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                            <path
                              fill-rule="evenodd"
                              d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"
                            />
                          </svg>
                          <span className="lg:block hidden">Update Post</span>
                        </p>
                      </Link>
                      <Link to="#" onClick={this.deleteConfirmed}>
                        <p className="flex hover:text-red-400 items-center leading-8 space-x-2 text-red-500 font-medium">
                          <svg
                            xmlns="http://www.w3.org/2000/svg"
                            width="16"
                            height="16"
                            fill="currentColor"
                            class="bi bi-trash-fill"
                            viewBox="0 0 16 16"
                          >
                            <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z" />
                          </svg>
                          <span className="lg:block hidden">Delete Post</span>
                        </p>
                      </Link>
                    </div>
                  </div>
                )}
            </div>
            <h2 className="display-2">{post.title}</h2>
            <p className="mb-5">{post.body}</p>
            <div className="card-body">
              <img
                src={`${process.env.REACT_APP_API_URL}/post/photo/${post._id}`}
                alt={post.title}
                onError={(i) => (i.target.src = `${DefaultPost}`)}
                className="img-thunbnail mb-3"
                style={{
                  height: "100%",
                  width: "100%",
                  objectFit: "cover",
                }}
              />
              {like ? (
                <button
                  className="flex lg:items-center text-xl"
                  onClick={likeToggle}
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    fill="currentColor"
                    style={{ color: "#db2777" }}
                    class="bi bi-heart-fill mr-2"
                    viewBox="0 0 16 16"
                  >
                    <path
                      fill-rule="evenodd"
                      d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"
                    />
                  </svg>
                  {likes} Like
                </button>
              ) : (
                <button
                  className="flex lg:items-center text-xl"
                  onClick={likeToggle}
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    fill="currentColor"
                    class="bi bi-heart-fill mr-2"
                    viewBox="0 0 16 16"
                  >
                    <path
                      fill-rule="evenodd"
                      d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"
                    />
                  </svg>
                  {likes} Like
                </button>
              )}
              <Link to={`/`}>
                <button class="bg-blue-600 font-semibold px-6 py-2 rounded-md text-white mt-5 ml-2">
                  Back to posts
                </button>
              </Link>
            </div>
            <Comment
              postId={post._id}
              comments={comments.reverse()}
              updateComments={updateComments}
            />
          </>
        )}
      </div>
    </div>
  );
}

export default SinglePost;
