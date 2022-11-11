import { useState } from "react";
import defaltAvatar from '../images/avatar.png'

export default function Sidebar({ users, setUsers, active, setActive }) {
    const [search, setSearch] = useState('');

    return (
        <div className="lg:w-4/12 bg-white border-r overflow-hidden dark:bg-gray-800 dark:border-[#ccc]">
            <div className="border-b px-4 py-4 dark:border-[#ccc]">
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
                                <div   className={`'block flex items-center py-3 px-4 space-x-3 hover:bg-gray-100 dark:hover:bg-gray-700 ${active?.id === user.id ? "bg-gray-100" : ""}'`}>
                                    <div className="w-12 h-12 rounded-full relative flex-shrink-0">
                                        <img
                                            src={
                                                user.avatar
                                                    ? user.avatar
                                                    : defaltAvatar
                                            }
                                            alt=""
                                            className="absolute h-full rounded-full w-full"
                                        />
                                        {/* <span className="absolute bg-green-500 border-2 border-white bottom-0 h-3 m-0.5 right-0 rounded-full shadow-md w-3"></span> */}
                                    </div>
                                    <div className="flex-1 min-w-0 relative text-gray-500">
                                        <h4 className="text-black font-semibold dark:text-white">{user.name}</h4>
                                        <span className="absolute right-0 top-1 text-xs">Sun</span>
                                        <p className="truncate">@{user.username}</p>
                                    </div>
                                </div>
                            </li>
                        ) : ''
                    })}
                </ul>
            </div>
        </div>
    );
}
