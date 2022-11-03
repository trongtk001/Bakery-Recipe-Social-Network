import { useState } from "react";

export default function Sidebar({ users, setUsers, active, setActive }) {
    const [search, setSearch] = useState('');

    return (
        <div className="lg:w-4/12 bg-white border-r overflow-hidden dark:bg-gray-800 dark:border-gray-600">
            <div className="border-b px-4 py-4 dark:border-gray-600">
                <div className="bg-gray-100 input-with-icon rounded-md dark:bg-gray-700">
                    <input onChange={(e) => setSearch(e.target.value)} id="autocomplete-input" type="text" placeholder="Search" className="bg-transparent max-h-10 shadow-none" />
                    <i className="icon-material-outline-search"></i>
                </div>
            </div>
            <div className="pb-16 w-full">
                <ul className="dark:text-gray-100">
                    {users?.map((user, i) => {
                        return user.name.toLowerCase().includes(search) ? (
                            <li key={i} onClick={() => setActive(user)} className="cursor-pointer">
                                <a href="#/"  className={`'block flex items-center py-3 px-4 space-x-3 hover:bg-gray-100 dark:hover:bg-gray-700 ${active?.id === user.id ? "bg-gray-100" : ""}'`}>
                                    <div className="w-12 h-12 rounded-full relative flex-shrink-0">
                                        <img
                                            src={
                                                user.avatar
                                                    ? user.avatar
                                                    : "https://images.squarespace-cdn.com/content/v1/54b7b93ce4b0a3e130d5d232/1519987020970-8IQ7F6Z61LLBCX85A65S/icon.png?format=1000w"
                                            }
                                            alt=""
                                            className="absolute h-full rounded-full w-full"
                                        />
                                        <span className="absolute bg-green-500 border-2 border-white bottom-0 h-3 m-0.5 right-0 rounded-full shadow-md w-3"></span>
                                    </div>
                                    <div className="flex-1 min-w-0 relative text-gray-500">
                                        <h4 className="text-black font-semibold dark:text-white">{user.name}</h4>
                                        <span className="absolute right-0 top-1 text-xs">Sun</span>
                                        <p className="truncate">@{user.username}</p>
                                    </div>
                                </a>
                            </li>
                        ) : ''
                    })}
                </ul>
            </div>
        </div>
    );
}
