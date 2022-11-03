import React, { useEffect, useState } from "react";
import { singlePost } from "./apiPost";
import { Link, useParams } from "react-router-dom";
import Comment from "./Comment";

function SinglePost() {
    const { postId } = useParams();
    const [post, setPost] = useState({
        id: 80,
        member: {
            id: 3,
            name: "trong",
            email: "trong@gmail.com",
            dob: "2001-10-17",
            username: "trong",
            password: null,
            avatar: null,
            roles: ["USER", "ADMIN"],
            token: null,
        },
        createDate: "2022-10-27T22:14:46.327Z",
        postBody: "Bánh Mochi Nhật Bản là công thức làm bánh truyền thống rất độc đáo của người dân đất nước mặt trời mọc.",
        recipe: {
            id: 80,
            name: "bánh Mochi",
            steps: [
                {
                    id: 39,
                    step: "Làm nhân bánh",
                    description:
                        "Cho đậu đỏ vào một cái thau ngâm với nước 1 đến 2 tiếng cho đậu đỏ mềm./nSau đó đem rửa lại thật sạch, để ráo nước rồi cho đậu vào một cái nồi, cho nước cốt dừa vào nấu cho đậu thật chín nhé. Khi đậu đã chín bạn tắt bếp để cho đậu đỏ nguội đi.",
                    image: "",
                    video: "",
                    ingredients: [
                        {
                            id: 1,
                            ingredients: "Bột bánh bông lan Meizan",
                            description:
                                "Meizan Hi-ratio cake flour giúp làm ra những chiếc bánh bông lan rất mềm, bông xốp, mịn màng do đây là loại bột đặc biệt có kích cỡ hạt siêu nhỏ, rất lý tưởng dùng cho các loại bánh bông lan cao cấp.",
                            unit: "Gói",
                            quantity: 2,
                        },
                    ],
                },
            ],
            tool: ["Dao", "Nồi"],
        },
        likes: [
            {
                id: 1,
                memberID: 3,
                postID: 80,
                action: null,
            },
            {
                id: 2,
                memberID: 2,
                postID: 80,
                action: null,
            },
        ],
    });
    // const [like, setLike] = useState(false);
    // const [likes, setLikes] = useState(0);
    // const [comments, setComments] = useState([]);

    // const checkLike = (likes) => {
    //     const userId = isAuthenticated() && isAuthenticated().user._id;
    //     let match = likes.indexOf(userId) !== -1;
    //     return match;
    // };
    useEffect(
        () => {
            singlePost(postId).then((data) => {
                setPost(data);
            });
        },
        // eslint-disable-next-line
        [postId]
    );

    console.log(post);
    // const updateComments = (comments) => {
    //     setComments(comments);
    // };

    // const deletePost = () => {
    //   const postId = this.props.match.params.postId;
    //   const token = isAuthenticated().token;
    //   remove(postId, token).then((data) => {
    //     if (data.error) {
    //       console.log(data.error);
    //     } else {
    //       // this.setState({ redirectToHome: true });
    //       console.log(data.error);
    //     }
    //   });
    // };

    // const deleteConfirmed = () => {
    //   let answer = window.confirm("Are you sure you want to delete your post?");
    //   if (answer) {
    //     deletePost();
    //   }
    // };

    return (
        <div className="flex justify-center w-full ">
            <div className="bg-white shadow rounded-md dark:bg-gray-900  w-full mx-4 lg:mx-0 max-w-[800px] mt-20 xl:mt-10 md:min-w-[800px] ">
                <div className="flex justify-between items-center px-4 py-3">
                    <div className="flex flex-1 items-center space-x-4">
                        <Link to={`/${post.member.id}`}>
                            <div className="bg-gradient-to-tr from-yellow-600 to-pink-600 p-0.5 rounded-full">
                                <img
                                    src={`${post.member.avatar}`}
                                    onError={(i) => (i.target.src = `https://source.unsplash.com/random/?bakery,bake,${post.member.name}`)}
                                    alt="avatar"
                                    className="bg-gray-200 border border-white rounded-full w-12 h-12"
                                />
                            </div>
                        </Link>
                        <span className="block capitalize font-semibold text-gray-500 dark:text-gray-100">
                            <p className="font-bold text-lg">
                                {post.member.name} <span className="text-gray-500 font-normal text-sm">@{post.member.username}</span>{" "}
                            </p>{" "}
                            on
                            {new Date(post.createDate).toDateString()}
                        </span>
                    </div>
                </div>
                <div>
                    <h5 className="px-4 mt-4 text-3xl text-pink-600  font-bold pb-4">{post.postBody}</h5>
                    <ul className="px-4 mb-2 flex gap-2 wrap">
                        {post.recipe.tool &&
                            post.recipe.tool?.map((item, index) => {
                                return <li className="border border-pink-600 text-pink-600 rounded-full px-2 py-1 min-w-40 text-center">#{item}</li>;
                            })}
                    </ul>
                    {post.recipe?.steps?.map((step, index) => {
                        return (
                            <div class="p-4 flex gap-4">
                                <p className="text-2xl font-semibold bg-pink-200 py-1 rounded-md text-center w-[40px] h-[40px] text-pink-600">{index + 1}</p>
                                <div className="flex-1">
                                    <p className="text-2xl font-semibold mb-4 text-pink-600">{step.step}</p>
                                    <p className="text-xl text-justify ">{step.description}</p>
                                    <ul className="my-4 ">
                                        <p className="text-lg font-bold">Nguyên liệu cần có cho bước {index + 1} :</p>
                                        {step.ingredients &&
                                            step.ingredients?.map((inn, index) => {
                                                return (
                                                    <li className="text-md ml-10 my-1 text-dark-900 flex items-center gap-2 ">
                                                        <svg xmlns="http://www.w3.org/2000/svg" fill="#db2777" className="h-4 w-4" viewBox="0 0 512 512">
                                                            <path d="M470.6 105.4c12.5 12.5 12.5 32.8 0 45.3l-256 256c-12.5 12.5-32.8 12.5-45.3 0l-128-128c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0L192 338.7 425.4 105.4c12.5-12.5 32.8-12.5 45.3 0z" />
                                                        </svg>
                                                        {inn.ingredients} - {inn.quantity} {inn.unit}
                                                    </li>
                                                );
                                            })}
                                    </ul>
                                    <div class="col-span-2 my-4">
                                        <img
                                            alt=""
                                            src={step.image}
                                            onError={(i) => (i.target.src = `https://source.unsplash.com/random/?bakery,bake,${post.id}`)}
                                            className="rounded-md w-full object-cover"
                                            style={{ height: "30rem" }}
                                        />
                                    </div>
                                </div>
                            </div>
                        );
                    })}
                </div>
                <Comment post={post} />
            </div>
        </div>
    );
}

export default SinglePost;
