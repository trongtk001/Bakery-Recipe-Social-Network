export default function FrChat({active, usersActive}) {
    return (
        <div className="px-5 py-4 flex uk-flex-between">                        
            <a href="/#" className="flex items-center space-x-3">
                <div className="w-10 h-10 rounded-full relative flex-shrink-0">
                    <img src={active ? active.avatar : "https://source.unsplash.com/random/?bakery,bake,hung"} alt="" className="h-full rounded-full w-full"/>
                    <span className={`absolute border-2 border-white bottom-0 h-3 m-0.5 mb-6 block right-0 rounded-full shadow-md w-3 ${usersActive?.includes(active?.name) ? 'bg-green-500 ' : 'bg-dark-500'}`}></span>
                </div>
                <div className="flex-1 min-w-0 relative text-gray-500">
                    <h4 className="font-semibold text-black text-lg">{active?.name}</h4>
                    <p className="font-semibold leading-3  text-sm">{usersActive?.includes(active?.name) ? 'Online ' : 'Offline'}</p>
                </div>
            </a>                        
            <a href="/#" className="flex hover:text-red-400 items-center leading-8 space-x-2 text-red-500 font-medium"> 
                <i className="uil-trash-alt"></i> <span className="lg:block hidden"> Delete </span> 
            </a>
        </div>
    )

}