/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.dialogflow.v2beta1;

import com.google.api.core.BetaApi;
import com.google.cloud.dialogflow.v2beta1.SessionsGrpc.SessionsImplBase;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@javax.annotation.Generated("by GAPIC")
@BetaApi
public class MockSessionsImpl extends SessionsImplBase {
  private ArrayList<GeneratedMessageV3> requests;
  private Queue<Object> responses;

  public MockSessionsImpl() {
    requests = new ArrayList<>();
    responses = new LinkedList<>();
  }

  public List<GeneratedMessageV3> getRequests() {
    return requests;
  }

  public void addResponse(GeneratedMessageV3 response) {
    responses.add(response);
  }

  public void setResponses(List<GeneratedMessageV3> responses) {
    this.responses = new LinkedList<Object>(responses);
  }

  public void addException(Exception exception) {
    responses.add(exception);
  }

  public void reset() {
    requests = new ArrayList<>();
    responses = new LinkedList<>();
  }

  @Override
  public void detectIntent(
      DetectIntentRequest request, StreamObserver<DetectIntentResponse> responseObserver) {
    Object response = responses.remove();
    if (response instanceof DetectIntentResponse) {
      requests.add(request);
      responseObserver.onNext((DetectIntentResponse) response);
      responseObserver.onCompleted();
    } else if (response instanceof Exception) {
      responseObserver.onError((Exception) response);
    } else {
      responseObserver.onError(new IllegalArgumentException("Unrecognized response type"));
    }
  }

  @Override
  public StreamObserver<StreamingDetectIntentRequest> streamingDetectIntent(
      final StreamObserver<StreamingDetectIntentResponse> responseObserver) {
    final Object response = responses.remove();
    StreamObserver<StreamingDetectIntentRequest> requestObserver =
        new StreamObserver<StreamingDetectIntentRequest>() {
          @Override
          public void onNext(StreamingDetectIntentRequest value) {
            if (response instanceof StreamingDetectIntentResponse) {
              responseObserver.onNext((StreamingDetectIntentResponse) response);
            } else if (response instanceof Exception) {
              responseObserver.onError((Exception) response);
            } else {
              responseObserver.onError(new IllegalArgumentException("Unrecognized response type"));
            }
          }

          @Override
          public void onError(Throwable t) {
            responseObserver.onError(t);
          }

          @Override
          public void onCompleted() {
            responseObserver.onCompleted();
          }
        };
    return requestObserver;
  }
}
