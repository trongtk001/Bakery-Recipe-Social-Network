import React, { Component, useState } from "react";
import { Link } from "react-router-dom";

class PostByUser extends Component {
  render() {
    const { posts ,a,setA} = this.props;
    return (
      <>
        <div class="flex items-center justify-between mt-8 space-x-3">
          <h1 class="flex-1 font-extrabold leading-none lg:text-2xl text-lg text-gray-900 tracking-tight uk-heading-line">
            <span>Explore</span>
          </h1>
          <div class="bg-white border border-2 border-gray-300 divide-gray-300 divide-x flex rounded-md shadow-sm dark:bg-gray-100">
            <div
        
              class="bg-gray300 flex h-10 items-center justify-center  w-10"
              data-tippy-placement="top"
              data-tippy=""
              data-original-title="Grid view"
              onClick={()=>setA(true)}

            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="16"
                height="16"
                fill="currentColor"
                class="bi bi-border-all"
                viewBox="0 0 16 16"
              className={a?"text-pink-600":""}


              >
                <path d="M0 0h16v16H0V0zm1 1v6.5h6.5V1H1zm7.5 0v6.5H15V1H8.5zM15 8.5H8.5V15H15V8.5zM7.5 15V8.5H1V15h6.5z" />
              </svg>
            </div>
            <div

              class="flex h-10 items-center justify-center w-10"
              data-tippy-placement="top"
              data-tippy=""
              data-original-title="List view"
              onClick={()=>setA(false)}

            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="16"
                height="16"
                fill="currentColor"
                class="bi bi-card-list"
              className={a?"":"text-pink-600"}

                viewBox="0 0 16 16"
              >
                <path d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h13zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z" />
                <path d="M5 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 5 8zm0-2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm0 5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm-1-5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zM4 8a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zm0 2.5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0z" />
              </svg>
            </div>
          </div>
        </div>
        <div className={`my-6  gap-1.5 hover:text-yellow-700 uk-link-reset grid ${a ? ' lg:grid-cols-3 grid-cols-2 ' : ''}`}>
          {posts.list?.length > 0
            ? posts.list?.map((post, i) => (
                <Link to={`/post/${post.id}`} key={i}>
                  {!a && (<div className="a">
                    <p className="font-bold text-center my-4 text-2xl text-pink-600 border-b py-4 border-pink-600">{post.postBody}</p>
                  </div>)}
                  <div
                    className={`bg-gray-200 max-w-full rounded-md relative overflow-hidden uk-transition-toggle ${a ? 'lg:h-64 h-40' : 'h-[40rem] w-full' }`}
                    tabIndex={0}
                  >
                    {
                      <img
                        src={post.recipe.steps[0] ? post.recipe.steps[0].image : ""}
                        onError={(i) =>
                          (i.target.src = `https://source.unsplash.com/random/?bakery,bake,1`)
                        }
                        className="w-full h-full absolute object-cover inset-0"
                      />
                    }

                    <div className="absolute bg-black bg-opacity-40 bottom-0 flex h-full items-center justify-center space-x-5 text-lg text-white uk-transition-scale-up w-full">
                       {post.likes.length}
                      <ion-icon
                        name="heart"
                        className="mr-1 md hydrated"
                        role="img"
                        aria-label="heart"
                      />
                      <ion-icon
                        name="chatbubble-ellipses"
                        className="mr-1 md hydrated"
                        role="img"
                        aria-label="chatbubble ellipses"
                      />
                    </div>
                  </div>
                  
                </Link>
              ))
            : "not found"}
        </div>
      </>
    );
  }
}

export default PostByUser;
