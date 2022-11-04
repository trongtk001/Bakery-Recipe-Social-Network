import React, { useEffect } from 'react';
import { useParams } from 'react-router-dom';

const IngredientEditPage = () => {
  const params = useParams();

  useEffect(() => {
    (async () => {
      try {
        const ingredientId = params.ingredientId;
        if (ingredientId) {
          const response = await axiosClient.get(`/ingredients/${ingredientId}`);
          delete response.token;
          reset(response);
          setPost(response);
        }
      } catch (error) {}
    })();
  }, []);

  return <div></div>;
};

export default IngredientEditPage;
