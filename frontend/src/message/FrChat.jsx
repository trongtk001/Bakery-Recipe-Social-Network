export default function FrChat({active,usersActive}) {

    console.log("userac",usersActive)
    return (
        <div class="px-5 py-4 flex uk-flex-between">                        
            <a href="#" class="flex items-center space-x-3">
                <div class="w-10 h-10 rounded-full relative flex-shrink-0">
                    <img src="https://source.unsplash.com/random/?bakery,bake,hung" alt="" class="h-full rounded-full w-full"/>
                    <span class={`'absolute border-2 border-white bottom-0 h-3 m-0.5 mb-6 block right-0 rounded-full shadow-md w-3 ${usersActive?.includes(active.name) ? 'bg-green-500 ' : 'bg-dark-500'}'`}></span>
                </div>
                <div class="flex-1 min-w-0 relative text-gray-500">
                    <h4 class="font-semibold text-black text-lg">{active.name}</h4>
                    <p class="font-semibold leading-3  text-sm">{usersActive?.includes(active.name) ? 'Online ' : 'Offline'}</p>
                </div>
            </a>                        
            <a href="#" class="flex hover:text-red-400 items-center leading-8 space-x-2 text-red-500 font-medium"> 
                <i class="uil-trash-alt"></i> <span class="lg:block hidden"> Delete </span> 
            </a>
        </div>
    )

}