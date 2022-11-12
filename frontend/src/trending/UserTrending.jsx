import { Link } from "react-router-dom"

export default function UserTrending({post}) {
        console.log(post)
        return (
            <div className="flex gap-4 mb-10  w-full overflow-x-auto" >
            {post && post?.map((item, index)=>{
                    return ( 
                        <Link key={index} to={`user/${item.id}`}  className="inline-block min-w-max">
                            <div className=" relative bg-gradient-to-tr from-yellow-600 to-pink-600 p-1 rounded-full transform -rotate-2 hover:rotate-3 transition hover:scale-105 m-1">
                                <img alt="" src={item.avatar ? item.avatar : 'https://images.squarespace-cdn.com/content/v1/54b7b93ce4b0a3e130d5d232/1519987020970-8IQ7F6Z61LLBCX85A65S/icon.png?format=1000w'} className="w-20 h-20 rounded-full border-2 border-white bg-gray-200"/>
                                <p  className="bg-gray-400 rounded-full w-8 h-8 flex justify-center items-center text-white border-4 border-white absolute right-2 bottom-0 bg-pink-600"><svg xmlns="http://www.w3.org/2000/svg" width='13px' hieght='13px' fill="#fff" viewBox="0 0 512 512"><path d="M47.6 300.4L228.3 469.1c7.5 7 17.4 10.9 27.7 10.9s20.2-3.9 27.7-10.9L464.4 300.4c30.4-28.3 47.6-68 47.6-109.5v-5.8c0-69.9-50.5-129.5-119.4-141C347 36.5 300.6 51.4 268 84L256 96 244 84c-32.6-32.6-79-47.5-124.6-39.9C50.5 55.6 0 115.2 0 185.1v5.8c0 41.5 17.2 81.2 47.6 109.5z"/></svg> </p>
                            </div>
                        </Link>
                    )
            })}
        </div>
    )
}