import React from "react";
import { Link } from "react-router-dom";
import { useIsHidden } from "../hooks/useIsHidden";
import Comment from "./Comment";

function DataPost({ post }) {


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
        {post.recipe  && (
          <>
            <span className="px-6 block capitalize font-semibold">
              name: {post.recipe.name}
            </span>
            <span className="px-6 block capitalize font-semibold">
              tool: {post.recipe.tool}
            </span>
            <span className="px-6 block capitalize font-semibold">
              steps: {post.recipe.steps}
            </span>
            <span className="px-6 block capitalize font-semibold">
              description: {post.recipe.description}
            </span>
          </>
        )}
        
        <Link to={`/post/${post.id}`}>
          {post.postImages.map((image, index) => (
            <div class="col-span-2" key={index}>
              {post.postImages.length === 1 ? (
                <img
                  src={`${image.image}`}
                  onError={(i) =>
                    (i.target.src = `https://source.unsplash.com/random/?bakery,bake,${image.id}`)
                  }
                  alt={image.id}
                  className="rounded-md w-full object-cover"
                  style={{ height: "30rem" }}
                />
              ) : (
                <img
                  src={`${image.image}`}
                  onError={(i) =>
                    (i.target.src = `https://source.unsplash.com/random/?bakery,bake,${image.id}`)
                  }
                  alt={image.id}
                  className="rounded-md w-full h-56 object-cover"
                />
              )}
            </div>
          ))}
        </Link>
      </div>
      <Comment post={post} />
    </div>
  );
}

export default DataPost;
