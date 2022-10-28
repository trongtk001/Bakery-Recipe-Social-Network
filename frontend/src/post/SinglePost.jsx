import React, { useEffect, useState } from "react";
import { singlePost, remove, likePost, unlike } from "./apiPost";
import { Link, useParams } from "react-router-dom";
import { isAuthenticated } from "../auth";
import Comment from "./Comment";

function SinglePost() {
  const { postId } = useParams();
  const [post, setPost] = useState({
    "id": 80,
    "member": {
        "id": 3,
        "name": "trong",
        "email": "trong@gmail.com",
        "dob": "2001-10-17",
        "username": "trong",
        "password": null,
        "avatar": null,
        "roles": [
            "USER",
            "ADMIN"
        ],
        "token": null
    },
    "createDate": "2022-10-27T22:14:46.327Z",
    "postBody": "Bánh Mochi Nhật Bản là công thức làm bánh truyền thống rất độc đáo của người dân đất nước mặt trời mọc.",
    "recipe": {
        "id": 80,
        "name": "bánh Mochi",
        "steps": [
            {
                "id": 39,
                "step": "Làm nhân bánh",
                "description": "Cho đậu đỏ vào một cái thau ngâm với nước 1 đến 2 tiếng cho đậu đỏ mềm./nSau đó đem rửa lại thật sạch, để ráo nước rồi cho đậu vào một cái nồi, cho nước cốt dừa vào nấu cho đậu thật chín nhé. Khi đậu đã chín bạn tắt bếp để cho đậu đỏ nguội đi.",
                "image": "",
                "video": "",
                "ingredients": [
                    {
                        "id": 1,
                        "ingredients": "Bột bánh bông lan Meizan",
                        "description": "Meizan Hi-ratio cake flour giúp làm ra những chiếc bánh bông lan rất mềm, bông xốp, mịn màng do đây là loại bột đặc biệt có kích cỡ hạt siêu nhỏ, rất lý tưởng dùng cho các loại bánh bông lan cao cấp.",
                        "unit": "Gói",
                        "quantity": 2
                    }
                ]
            }
        ],
        "tool": [
            "Dao",
            "Nồi"
        ]
    },
    "likes": [
        {
            "id": 1,
            "memberID": 3,
            "postID": 80,
            "action": null
        },
        {
            "id": 2,
            "memberID": 2,
            "postID": 80,
            "action": null
        }
    ]
});
  const [like, setLike] = useState(false);
  const [likes, setLikes] = useState(0);
  const [comments, setComments] = useState([]);


  const checkLike = (likes) => {
    const userId = isAuthenticated() && isAuthenticated().user._id;
    let match = likes.indexOf(userId) !== -1;
    return match;
  };
  useEffect( async () => {
      singlePost(postId).then((data) => {
        if (data.error) {
          console.log(data.error);
        } else {
          console.log(data)
           setPost(data);
        }
      });
    },
    // eslint-disable-next-line
    [postId]
  );

    console.log(post)
  const updateComments = (comments) => {
    setComments(comments);
  };


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
   <div className="flex justify-center w-full">
     <div className="bg-white shadow rounded-md dark:bg-gray-900 -mx-2 lg:mx-0 max-w-[800px] ">
      <div className="flex justify-between items-center px-4 py-3">
        <div className="flex flex-1 items-center space-x-4">
          <Link to={`/${post.member.id}`}>
            <div className="bg-gradient-to-tr from-yellow-600 to-pink-600 p-0.5 rounded-full">
              <img
                src={`${post.member.avatar}`}
                onError={(i) =>
                  (i.target.src = `https://source.unsplash.com/random/?bakery,bake,${post.member.name}`)
                }
                alt="avatar"
                className="bg-gray-200 border border-white rounded-full w-8 h-8"
              />
            </div>
          </Link>
          <span className="block capitalize font-semibold text-gray-500 dark:text-gray-100">
            <p className="font-bold">{post.member.name} </p> on
            {new Date(post.createDate).toDateString()}
          </span>
        </div>
      </div>
    <div>
      <h5 className="px-4 text-3xl">{post.postBody}</h5>
      
      {/* <Link to={`/post/${post.id}`}>

          <div class="col-span-2">
              <img
                src={`${post.recipe?.steps[0]?.image 
                  ? post.recipe?.steps[0]?.image 
                  : post.recipe?.steps[1]?.image 
                  ? post.recipe?.steps[1]?.image 
                  : `https://source.unsplash.com/random/?bakery,bake,${post.member.name}`}`}
                onError={(i) =>
                  (i.target.src = `https://source.unsplash.com/random/?bakery,bake,${post.id}`)
                }
                className="rounded-md w-full object-cover"
                style={{ height: "30rem" }}
              />
          </div>

      </Link> */}
      {post.recipe?.steps?.map((step, index) => {
          return (<div class='p-4'>
                    <p className="text-2xl font-semibold">Bước {index + 1} {step.step}</p>
                    <p className="text-xl ">{step.description}</p>
                    <div class="col-span-2">
                      <img
                        src={step.image}
                        onError={(i) =>
                          (i.target.src = `https://source.unsplash.com/random/?bakery,bake,${post.id}`)
                        }
                        className="rounded-md w-full object-cover"
                        style={{ height: "30rem" }}
                      />
                </div>
                </div>
                )
      })
    }
    </div>
    <Comment post={post} />
  </div>
   </div>
  );

}

export default SinglePost;
