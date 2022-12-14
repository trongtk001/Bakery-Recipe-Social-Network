import React, { useEffect, useRef, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import { useParams } from 'react-router-dom';
import axiosClient from '../api/axiosClient';
import { Controller, useFieldArray, useForm } from 'react-hook-form';

import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import styled from 'styled-components';

const defaultValues = {
  createDate: null,
  postBody: '',
  recipe: {
    name: '',
    steps: [],
    tool: [],
  },
};

const PostEditPage = () => {
  const params = useParams();
  const [post, setPost] = useState();
  // eslint-disable-next-line
  const titleRef = useRef(null);
  const { register, reset, control, handleSubmit } = useForm({
    defaultValues: defaultValues,
  });

  const { fields, append, remove } = useFieldArray({
    control,
    name: 'recipe.steps',
  });

  const { fields: fieldsTool, append: appendTool, remove: removeTool } = useFieldArray({
    control,
    name: 'recipe.tool',
  });

  const postId = params.postId;

  useEffect(() => {
    (async () => {
      try {
        if (postId) {
          const response = await axiosClient.get(`/post/${postId}`);
          delete response.token;
          reset(response);
          setPost(response);
        } else {
          reset(defaultValues);
          setPost(defaultValues);
        }
      } catch (error) {}
    })();
    // eslint-disable-next-line
  }, []);

  const onSubmitForm = async (formValues) => {
    if (formValues) {
      try {
        if (postId) {
          await axiosClient.put(`/post/${postId}`, {
            ...formValues,
          });
        } else {
          await axiosClient.post(`/post`, {
            ...formValues,
          });
        }
      } catch (error) {}
    }
    console.log({ formValues });
  };

  return (
    <Container fluid='md'>
      {post && (
        <Form id='postForm' onSubmit={handleSubmit(onSubmitForm)}>
          <Form.Group className='mb-3'>
            <Form.Label>T??n c??ng th???c</Form.Label>
            <Controller
              control={control}
              name='recipe.name'
              render={({ field }) => {
                return <Form.Control {...field} />;
              }}
            />
          </Form.Group>
          {postId && (
            <PostInfo>
              <span>
                <span className='bold'>T??c gi???:</span> {post.member.name}
              </span>{' '}
              <span>
                <span className='bold'>Th???i gian: </span>
                <time>{post.createDate}</time>
              </span>
              <span>
                <span className='bold'>L?????t th??ch: </span>
                <Button>{post.likes.length}</Button>
              </span>
            </PostInfo>
          )}
          <Form.Group className='mb-3'>
            <Form.Label>M?? t???</Form.Label>
            <Controller
              control={control}
              name='postBody'
              render={({ field }) => {
                return <Form.Control as='textarea' style={{ minHeight: '200px' }} {...field} />;
              }}
            />
          </Form.Group>
          <h5>D???ng c???: </h5>
          <div
            style={{
              display: 'flex',
              padding: '24px 0',
              alignItems: 'center',
              gap: '8px',
              flexWrap: 'wrap',
            }}
          >
            {fieldsTool.map((field, index) => (
              <Row style={{ margin: '24px 0' }}>
                <Col>
                  <Form.Control
                    key={field.id}
                    {...register(`recipe.tool.${index}`)}
                    placeholder='T??n d???ng c???'
                  />
                </Col>
                <Col>
                  <Button variant='outline-danger' onClick={() => removeTool(index)}>
                    X??a
                  </Button>
                </Col>
              </Row>
            ))}
          </div>
          <Button variant='outline-warning' onClick={() => appendTool('')}>
            + Th??m d???ng c???
          </Button>
          <h3 style={{ marginTop: '50px' }}>H?????ng d???n</h3>
          {fields.map((field, index) => (
            <Row style={{ margin: '24px 0' }}>
              <Col>
                <Form.Control
                  key={field.id}
                  {...register(`recipe.steps.${index}.step`)}
                  placeholder='T??n'
                />
              </Col>
              <Col>
                <Form.Control
                  as='textarea'
                  key={field.id}
                  {...register(`recipe.steps.${index}.description`)}
                  placeholder='M?? t???'
                />
              </Col>
              <Col>
                <Form.Control
                  key={field.id}
                  {...register(`recipe.steps.${index}.image`)}
                  placeholder='Link ???nh minh h???a'
                />
              </Col>
              <Col>
                <Button variant='outline-danger' onClick={() => remove(index)}>
                  X??a
                </Button>
              </Col>
            </Row>
          ))}
          <Row>
            <Col>
              <Button
                variant='outline-warning'
                onClick={() =>
                  append({
                    step: '',
                    description: '',
                    image: '',
                  })
                }
              >
                + Th??m b?????c
              </Button>
            </Col>
          </Row>
        </Form>
      )}
      <ButtonActionGroup>
        <Button variant='secondary'>H???y</Button>
        <Button type='submit' form='postForm' variant='success'>
          L??u
        </Button>
      </ButtonActionGroup>
    </Container>
  );
};

const ButtonActionGroup = styled.div`
  display: flex;
  justify-content: end;
  align-items: center;
  gap: 12px;
`;

const PostInfo = styled.div`
  display: flex;
  align-items: center;
  gap: 30px;
`;

export default PostEditPage;
