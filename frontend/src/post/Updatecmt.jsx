import { useState } from "react";
import { useIsLogin } from "../hooks/useIsLogin";
import { useDispatch } from "react-redux";
import { delComment, updateComment } from "../store/actions/post.action";
export default function Updatecmt({ comment, setListComment}) {
  const { isLogin, avatar } = useIsLogin();
    const dispatch = useDispatch();
    const [update, stUpdate] = useState(false);
    const [updatecmt, setUpdatecmt] = useState(comment.commentDetail);

    const delCMT = async (id) => {
        dispatch(delComment(id, setListComment));
    }

    const updateCMT = async (id) => {
        stUpdate(!update);
        dispatch(updateComment(id,updatecmt, setListComment));
    }

    return (
        <div className="">
            <div className="flex justify-end gap-3">
            <p>{isLogin.id === comment.member.id && <span className="text-sm  text-red-500  cursor-pointer" onClick={()=> delCMT(comment.id)}>Xóa</span>}</p>
            <p>{isLogin.id === comment.member.id && <span className="text-sm  text-dark-500  cursor-pointer" onClick={()=> stUpdate(!update)}>Sửa </span>}</p>
            {update &&
            (<div className="flex gap-2 ">
                    <input type="text" className="border border-gray-300 rounded-md px-2 py-1" value={updatecmt} onChange={(e)=> setUpdatecmt(e.target.value)} />
                    <p className="cursor-pointer hover:text-pink-500"  onClick={updateCMT}>Save</p>
            </div>)
            }
            </div>
        </div>
    
    )
}