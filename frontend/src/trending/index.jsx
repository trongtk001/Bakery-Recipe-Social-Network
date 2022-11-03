import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { listAll } from "../post/apiPost";
import { findPeople } from "../user/apiUser";
import UserSuggest from "./UserSuggest";
import UserTrending from "./UserTrending";

export default function Trending() {
    const [dataList, setDataList] = useState([]);
    const [users, setUsers] = useState({ list: [] });

    useEffect(() => {
        async function fetchData() {
            setDataList(await listAll());
            findPeople().then((data) => {
                if (data) {
                    setUsers(data);
                }
            });
        }
        fetchData();
    }, []);

    return (
        <div className="p-10">
            <h1 class="font-extrabold leading-none mb-6 mt-8 lg:text-2xl text-lg text-gray-900 tracking-tight">People </h1>
            <UserTrending post={users}></UserTrending>
            <h1 class="font-extrabold leading-none mb-6 mt-8 lg:text-2xl text-lg text-gray-900 tracking-tight">Suggestion</h1>
            <UserSuggest post={users}></UserSuggest>
            <h1 class="font-extrabold leading-none mb-6 mt-8 lg:text-2xl text-lg text-gray-900 tracking-tight">Post</h1>

            <div class="mt-6 grid lg:grid-cols-3 grid-cols-2 gap-3 hover:text-yellow-700 uk-link-reset">
                {dataList &&
                    dataList.map((item, index) => {
                        console.log(item);
                        return (
                            <div key={index} className="aspect-square">
                                <div class="bg-blue-400 max-w-full h-full w-full rounded-lg relative overflow-hidden shadow uk-transition-toggle">
                                    <Link to={`post/${item.id}`} uk-toggle>
                                        <img
                                            alt=""
                                            src={item.recipe.steps.length >= 1 && item.recipe.steps[0].image}
                                            class="w-full h-full absolute object-cover inset-0"
                                            onError={(i) => (i.target.src = `https://source.unsplash.com/random/?bakery,bake,${index}`)}
                                        />
                                    </Link>
                                    <div class="flex flex-1 items-center absolute bottom-0 w-full p-3 text-white custom-overly1 uk-transition-slide-bottom-medium">
                                        <a href="/#" class="lg:flex flex-1 items-center hidden">
                                            <div class="bg-gradient-to-tr from-yellow-600 to-pink-600 p-1 rounded-full transform -rotate-2 hover:rotate-3 transition hover:scale-105 m-0.5 mr-2">
                                                <img
                                                    alt=""
                                                    src={
                                                        item.member.avatar
                                                            ? item.member.avatar
                                                            : "https://images.squarespace-cdn.com/content/v1/54b7b93ce4b0a3e130d5d232/1519987020970-8IQ7F6Z61LLBCX85A65S/icon.png?format=1000w"
                                                    }
                                                    class="bg-gray-200 border border-white rounded-full w-10 h-10"
                                                />
                                            </div>
                                            <div> {item.member.name} </div>
                                        </a>
                                        <div class="flex space-x-2 flex-1 lg:flex-initial justify-around">
                                            <a href="/#">
                                                {" "}
                                                <i class="uil-heart"></i>
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
