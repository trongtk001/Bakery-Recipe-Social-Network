import { getShare } from "../post/apiPost";
import { useIsLogin } from "../hooks/useIsLogin";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Posts from "../post/Posts";
export default function Saved() {
    const {isLogin} = useIsLogin();
    const [share, setShare] = useState([]);
    useEffect(async() => {
        const saved = await getShare(isLogin.id, isLogin.token)
        setShare(saved.list)
    }, []);

    return (
        <div className="w-full flex justify-center">
            <Posts className='mx-auto' post={share} />
            {share &&
                share.map((item, index) => {
                    return (
                        <div key={index} className="aspect-square">
                            <div className="bg-blue-400 max-w-full h-full w-full rounded-lg relative overflow-hidden shadow ">
                                <Link to={`post/${item.id}`} uk-toggle="true">
                                    <img
                                        alt=""
                                        src={item.post.recipe.steps.length >= 1 && item.post.recipe.steps[0].image}
                                        className="w-full h-full absolute object-cover inset-0"
                                        onError={(i) => (i.target.src = `https://source.unsplash.com/random/?bakery,bake,${index}`)}
                                    />
                                </Link>
                                <div className="flex flex-1 items-center absolute bottom-0 w-full p-3 text-white custom-overly1 ">
                                    <a href="/#" className="lg:flex flex-1 items-center hidden">
                                        <div className="bg-gradient-to-tr from-yellow-600 to-pink-600 p-1 rounded-full transform -rotate-2 hover:rotate-3 transition hover:scale-105 m-0.5 mr-2">
                                            <img
                                                alt=""
                                                src={
                                                    item.post.member.avatar
                                                        ? item.post.member.avatar
                                                        : "https://images.squarespace-cdn.com/content/v1/54b7b93ce4b0a3e130d5d232/1519987020970-8IQ7F6Z61LLBCX85A65S/icon.png?format=1000w"
                                                }
                                                className="bg-gray-200 border border-white rounded-full w-10 h-10"
                                            />
                                        </div>
                                        <div> {item.post.member.name} </div>
                                    </a>
                                    <div className="flex space-x-2 flex-1 lg:flex-initial justify-around">
                                        <a href="/#">
                                            {" "}
                                            <i className="uil-heart"></i>
                                            {item.post.likes.length} Like{" "}
                                        </a>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    );
                })}
        </div>
    )
}