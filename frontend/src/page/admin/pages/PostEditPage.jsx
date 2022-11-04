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
            <Form.Label>Tên công thức</Form.Label>
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
                <span className='bold'>Tác giả:</span> {post.member.name}
              </span>{' '}
              <span>
                <span className='bold'>Thời gian: </span>
                <time>{post.createDate}</time>
              </span>
              <span>
                <span className='bold'>Lượt thích: </span>
                <Button>{post.likes.length}</Button>
              </span>
            </PostInfo>
          )}
          <Form.Group className='mb-3'>
            <Form.Label>Mô tả</Form.Label>
            <Controller
              control={control}
              name='postBody'
              render={({ field }) => {
                return <Form.Control as='textarea' style={{ minHeight: '200px' }} {...field} />;
              }}
            />
          </Form.Group>
          <h5>Dụng cụ: </h5>
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
                    placeholder='Tên dụng cụ'
                  />
                </Col>
                <Col>
                  <Button variant='outline-danger' onClick={() => removeTool(index)}>
                    Xóa
                  </Button>
                </Col>
              </Row>
            ))}
          </div>
          <Button variant='outline-warning' onClick={() => appendTool('')}>
            + Thêm dụng cụ
          </Button>
          <h3 style={{ marginTop: '50px' }}>Hướng dẫn</h3>
          {fields.map((field, index) => (
            <Row style={{ margin: '24px 0' }}>
              <Col>
                <Form.Control
                  key={field.id}
                  {...register(`recipe.steps.${index}.step`)}
                  placeholder='Tên'
                />
              </Col>
              <Col>
                <Form.Control
                  as='textarea'
                  key={field.id}
                  {...register(`recipe.steps.${index}.description`)}
                  placeholder='Mô tả'
                />
              </Col>
              <Col>
                <Form.Control
                  key={field.id}
                  {...register(`recipe.steps.${index}.image`)}
                  placeholder='Link ảnh minh họa'
                />
              </Col>
              <Col>
                <Button variant='outline-danger' onClick={() => remove(index)}>
                  Xóa
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
                + Thêm bước
              </Button>
            </Col>
          </Row>
        </Form>
      )}
      <ButtonActionGroup>
        <Button variant='secondary'>Hủy</Button>
        <Button type='submit' form='postForm' variant='success'>
          Lưu
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
