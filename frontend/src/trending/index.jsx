import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { listTrending } from "../post/apiPost";
import { findPeople, findPeople2 } from "../user/apiUser";
import UserSuggest from "./UserSuggest";
import UserTrending from "./UserTrending";

export default function Trending() {
    const [dataList, setDataList] = useState([]);
    const [users, setUsers] = useState();
    const [users2, setUsers2] = useState({ list: [] });

    useEffect(() => {
        async function fetchData() {
            setDataList(await listTrending());
            findPeople2().then((data) => {
                if (data) {
                    setUsers(data);
                }
            });
            findPeople().then((data) => {
                if (data) {
                    setUsers2(data);
                }
            });
        }
        fetchData();
    }, []);

    return (
        <div className="p-4">
            <h1 className="font-extrabold leading-none mb-6 lg:text-2xl text-lg text-gray-900 tracking-tight">People </h1>
            <UserTrending post={users}></UserTrending>
            <h1 className="font-extrabold leading-none mb-6 mt-8 lg:text-2xl text-lg text-gray-900 tracking-tight">Suggestion</h1>
            <UserSuggest post={users2}></UserSuggest>
            <h1 className="font-extrabold leading-none mb-6 mt-8 lg:text-2xl text-lg text-gray-900 tracking-tight">Post</h1>

            <div className="mt-6 grid lg:grid-cols-3 grid-cols-2 gap-3 hover:text-yellow-700 uk-link-reset">
                {dataList &&
                    dataList.map((item, index) => {
                        return (
                            <div key={index} className="aspect-square">
                                <div className="bg-blue-400 max-w-full h-full w-full rounded-lg relative overflow-hidden shadow ">
                                    <Link to={`post/${item.id}`} uk-toggle="true">
                                        <img
                                            alt=""
                                            src={item.recipe.steps.length >= 1 && item.recipe.steps[0].image}
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
                                                        item.member.avatar
                                                            ? item.member.avatar
                                                            : "https://images.squarespace-cdn.com/content/v1/54b7b93ce4b0a3e130d5d232/1519987020970-8IQ7F6Z61LLBCX85A65S/icon.png?format=1000w"
                                                    }
                                                    className="bg-gray-200 border border-white rounded-full w-10 h-10"
                                                />
                                            </div>
                                            <div> {item.member.name} </div>
                                        </a>
                                        <div className="flex space-x-2 flex-1 lg:flex-initial justify-around">
                                            <a href="/#">
                                                {" "}
                                                <i className="uil-heart"></i>
                                                {item.likes.length} Like{" "}
                                            </a>
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>
                        );
                    })}
            </div>
        </div>
    );
}
