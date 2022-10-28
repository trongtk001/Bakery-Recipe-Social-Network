import React from "react";
import { Link } from "react-router-dom";
import { useIsHidden } from "../hooks/useIsHidden";
import Comment from "./Comment";

function DataPost({ post }) {

console.log(post)
  return (
    <div className="bg-white shadow rounded-md dark:bg-gray-900 -mx-2 lg:mx-0">
      <div className="flex justify-between items-center px-4 py-3">
        <div className="flex flex-1 items-center space-x-4">
          <Link to={`/${post.member.id}`}>
            <div className="bg-gradient-to-tr from-yellow-600 to-pink-600 p-0.5 rounded-full">
              <img
                src={`${post.member.avatar}`}
                onError={(i) =>
                  (i.target.src = `https://source.unsplash.com/random/?bakery,bake,${post.member.name}`)
                }
                alt="avatar"
                className="bg-gray-200 border border-white rounded-full w-8 h-8"
              />
            </div>
          </Link>
          <span className="block capitalize font-semibold text-gray-500 dark:text-gray-100">
            <p className="font-bold">{post.member.name} </p> on
            {new Date(post.createDate).toDateString()}
          </span>
        </div>
      </div>
      <div>
        <h5 className="px-4">{post.postBody}</h5>
        
        <Link to={`/post/${post.id}`}>

            <div class="col-span-2">
                <img
                  src={`${post.recipe?.steps[0]?.image 
                    ? post.recipe?.steps[0]?.image 
                    : post.recipe?.steps[1]?.image 
                    ? post.recipe?.steps[1]?.image 
                    : `https://source.unsplash.com/random/?bakery,bake,${post.member.name}`}`}
                  onError={(i) =>
                    (i.target.src = `https://source.unsplash.com/random/?bakery,bake,${post.id}`)
                  }
                  className="rounded-md w-full object-cover"
                  style={{ height: "30rem" }}
                />
            </div>

        </Link>
      </div>
      <Comment post={post} />
    </div>
  );
}

export default DataPost;
