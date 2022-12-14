import { async } from "@firebase/util";
import { clippingParents } from "@popperjs/core";
import axios from "axios";
import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import Load from "../components/load";
import { useIsHidden } from "../hooks/useIsHidden";
import { useIsLogin } from "../hooks/useIsLogin";
import { delComment, getComment, postComment, savePost } from "../store/actions/post.action";
import { like } from "../user/apiUser";
import Updatecmt from "./Updatecmt";

function Comment({ post }) {
  const dispatch = useDispatch();
  const [ hidden, handleClick ] = useState(true);
  const { isLogin, avatar } = useIsLogin();
  const [loading, setLoading] = useState(false);
  const [listComment, setListComment] = useState(null);
  const [likes,setLikes] = useState(false);
  const [total,setTotal] = useState(post.likes.length);

  const [text, setText] = useState("");
  const [save, setSave] = useState(false);


  console.log( post.likes.map((like)=>like.memberID).includes(isLogin.id))

  useEffect(()=>{

    dispatch(getComment(post.id, setListComment));
     setTotal(post.likes.length);
     setLikes(()=> post.likes.map((like)=>like.memberID).includes(isLogin.id))
  
  },[post])

  console.log(post)
  // const onClickPostId = (id) => {
  //   handleClick();
  //   setLoading(true);
  //   dispatch(getComment(id, setListComment));
  //   setLoading(false);
  // };
  const fomatDate = (date)=>{
    const date1 = new Date(date);
    const date2 = new Date();
    const diffTime = Math.abs(date2 - date1);
    let diffDays; 
    if(diffTime > 3600000){
      diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + ' ngày trước';
    } else{
      diffDays = Math.ceil(diffTime / (1000 * 60 )) + ' phút trước';
    }
    return diffDays;
  }



  const onClickLike = async(e)=>{
     await like(isLogin.id, e)
      setLikes(!likes)
      setTotal(likes ? total - 1 : total + 1)
  }
  const shareToFacebook = ()=>{
    dispatch(savePost(post.id, isLogin.id, setSave));
  }

  const onComment = (e) => {
    e.preventDefault();
    dispatch(
      postComment(
        text,
        avatar,
        isLogin.id,
        post.id,
        setListComment,
        listComment
      )
    );
    setText("");
  };
  return (
    <div className="py-3 px-4 space-y-3">
      <div className="flex space-x-4 lg:font-bold">
        <button
        onClick={()=>onClickLike(post.id)}
        className={likes ? '!text-red-500 flex items-center space-x-2' : 'flex items-center space-x-2 '} >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 20 20"
            fill="currentColor"
            width={22}
            height={22}
            className=''
          >
            <path d="M2 10.5a1.5 1.5 0 113 0v6a1.5 1.5 0 01-3 0v-6zM6 10.333v5.43a2 2 0 001.106 1.79l.05.025A4 4 0 008.943 18h5.416a2 2 0 001.962-1.608l1.2-6A2 2 0 0015.56 8H12V4a2 2 0 00-2-2 1 1 0 00-1 1v.667a4 4 0 01-.8 2.4L6.8 7.933a4 4 0 00-.8 2.4z" />
          </svg>
          <div>  {total } Like</div>
        </button>
        <button
          className="flex items-center space-x-2"
          // onClick={() => onClickPostId(post.id)}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 20 20"
            fill="currentColor"
            width={22}
            height={22}
            className="dark:text-gray-100 text-gray-500"
          >
            <path
              fillRule="evenodd"
              d="M18 5v8a2 2 0 01-2 2h-5l-5 4v-4H4a2 2 0 01-2-2V5a2 2 0 012-2h12a2 2 0 012 2zM7 8H5v2h2V8zm2 0h2v2H9V8zm6 0h-2v2h2V8z"
              clipRule="evenodd"
            />
          </svg>
          <div> Comment</div>
        </button>
        <div className="flex items-center space-x-2 flex-1 justify-end">
          
        </div>
        <button className="flex items-center space-x-2 flex-1 justify-end">
        <svg onClick={shareToFacebook} width={20}
            height={20}
            fill={save? '#f00000' : 'currentColor'}
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 384 512">
            <path d="M336 0h-288C21.49 0 0 21.49 0 48v431.9c0 24.7 26.79 40.08 48.12 27.64L192 423.6l143.9 83.93C357.2 519.1 384 504.6 384 479.9V48C384 21.49 362.5 0 336 0zM336 452L192 368l-144 84V54C48 50.63 50.63 48 53.1 48h276C333.4 48 336 50.63 336 54V452z"/></svg>
          <div ></div>
        </button>
      </div>
      {hidden && (
        <>
          <div className="border-t pt-4 mb-6 space-y-4 dark:border-gray-600">
            {loading ? (
              <Load isSmall={true} />
            ) : (
              <>
                {listComment && (
                  <>
                    {listComment.length > 0 ? (
                      <>
                        {listComment.map((comment, i) => (
                          <div className="flex" key={i}>
                            <div className="w-10 h-10 rounded-full relative flex-shrink-0">
                              <img
                                src={comment.image ? comment.image : 'https://firebasestorage.googleapis.com/v0/b/bakery-9a92d.appspot.com/o/images%2Favatardefault_92824.webp0049579c-755c-41f3-9785-e443b6b03679?alt=media&token=17bf565a-0fbf-4b5a-afa8-6866564608c7'}
                                alt=""
                                className="absolute h-full rounded-full w-full"
                              />
                            </div>
                            <div>
                            <div className="text-gray-700 py-2 px-3 rounded-md bg-gray-100 relative lg:ml-5 ml-2   dark:bg-gray-800 dark:text-gray-100">
                              <p className="leading-6"> 
                                <span className="min-w-max font-bold ">{comment.member.name ? comment.member.name :  isLogin.name } </span> 
                                {comment.commentDetail} <span className="text-sm opacity-50">{fomatDate(comment.createDate)}</span> 
                                <i className="uil-grin-tongue-wink"> </i>
                              </p>
                              <div className="absolute w-3 h-3 top-3 -left-1 bg-gray-100 transform rotate-45 dark:bg-gray-800" />
                            </div>
                              <div className="">
                                  <Updatecmt comment={comment} setListComment={setListComment} ></Updatecmt>
                              </div>
                            </div>
                          </div>
                        ))}
                      </>
                    ) : (
                      <div className="flex">comment not found</div>
                    )}
                  </>
                )}
              </>
            )}
          </div>
          <form onSubmit={onComment}>
            <div className="bg-gray-100 bg-gray-100 rounded-full rounded-md relative dark:bg-gray-800">
              <input
                type="text"
                placeholder="Add your Comment.."
                className="bg-transparent max-h-10 shadow-none"
                value={text}
                onChange={(e) => setText(e.target.value)}
              />
            </div>
          </form>
        </>
      )}
    </div>
  );
}

export default Comment;
