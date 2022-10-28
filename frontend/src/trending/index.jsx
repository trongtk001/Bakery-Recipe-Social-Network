import { useEffect, useState } from "react"
import { listAll } from "../post/apiPost"

export default function Trending(){
    const [dataList, setDataList] = useState([])
    useEffect(async()=>{
        const  res = await listAll()
        setDataList(res)
        
    },[])
    console.log(dataList)
    return (
        <div className="p-10">
                <h1 class="font-extrabold leading-none mb-6 mt-8 lg:text-2xl text-lg text-gray-900 tracking-tight"> Trending </h1>
                <div class="mt-6 grid lg:grid-cols-3 grid-cols-2 gap-3 hover:text-yellow-700 uk-link-reset">
                    
                    {dataList &&  dataList.map((item, index)=>{
                        return (
                            <div key={index}>
                                <div
                                    class="bg-blue-400 max-w-full lg:h-56  h-48 rounded-lg relative overflow-hidden shadow uk-transition-toggle">
                                    <a href="#story-modal" uk-toggle>
                                        <img src={'https://source.unsplash.com/random/?bakery,bake,1'} class="w-full h-full absolute object-cover inset-0" />
                                    </a>
                                    <div
                                        class="flex flex-1 items-center absolute bottom-0 w-full p-3 text-white custom-overly1 uk-transition-slide-bottom-medium">
                                        <a href="#" class="lg:flex flex-1 items-center hidden">
                                            <div class="bg-gradient-to-tr from-yellow-600 to-pink-600 p-1 rounded-full transform -rotate-2 hover:rotate-3 transition hover:scale-105 m-0.5 mr-2">
                                                <img src={item.member.avatar}
                                                    class="bg-gray-200 border border-white rounded-full w-10 h-10"/>
                                            </div>
                                            <div> {item.member.name} </div>
                                        </a>
                                        <div class="flex space-x-2 flex-1 lg:flex-initial justify-around">
                                            <a href="#"> <i class="uil-heart"></i> 150 </a>
                                            <a href="#"> <i class="uil-heart"></i> 30 </a>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                        )
                    })}
                    
                </div>
            </div>
    )
}