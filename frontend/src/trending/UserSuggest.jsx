import { Link } from "react-router-dom";

export default function UserSuggest({ post }) {
    return (
        <div className="flex gap-4 mb-10  w-full overflow-x-auto">
            {post.list &&
                post.list?.map((item, index) => {
                    return (
                        <Link to={`user/${item.id}`} key={index} className="inline-block min-w-max h-full">
                            <div className="bg-gray-200 max-w-full w-[300px] h-[300px] rounded-lg relative overflow-hidden">
                                <img
                                    alt=""
                                    src={item.avatar ? item.avatar : "https://images.squarespace-cdn.com/content/v1/54b7b93ce4b0a3e130d5d232/1519987020970-8IQ7F6Z61LLBCX85A65S/icon.png?format=1000w"}
                                    className="w-full h-full absolute object-cover inset-0"
                                />
                                <p href="/#" className="absolute right-3 top-3 bg-black bg-opacity-60 rounded-full">
                                </p>
                                <div className="absolute bottom-0 p-4 w-full custom-overly1">
                                    <div className="flex justify-between align-bottom flex-wrap text-white">
                                        <div className="w-full truncate text-lg">{item.name}</div>
                                        <div className="leading-5 text-sm">
                                            <div>{item.dob}</div>
                                        </div>
                                        <p href="/#" className="absolute right-3 bottom-3 rounded-full bg-gradient-to-tr from-blue-500 to-purple-700">
                                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" className="fill-current h-6 m-1.5 text-white w-6">
                                                <path d="M8 9a3 3 0 100-6 3 3 0 000 6zM8 11a6 6 0 016 6H2a6 6 0 016-6zM16 7a1 1 0 10-2 0v1h-1a1 1 0 100 2h1v1a1 1 0 102 0v-1h1a1 1 0 100-2h-1V7z"></path>
                                            </svg>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </Link>
                    );
                })}
        </div>
    );
}
