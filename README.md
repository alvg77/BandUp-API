# BandUp-API

## Installation
- Clone the project locally
- In `src/main/resources` add a `.env` file with the following data:
```
POSTGRES_DB=example
POSTGRES_USER=example
POSTGRES_PASSWORD=example
JWT_SECRET=example
```
- For the AWS integration with S3, you have to set up a bucket with are `EU_CENTRAL_1` and provide file with credentials, located in `~/.aws/`, otherwise the application won't run. More information about configuring AWS credentials locally can be found <a href="https://docs.aws.amazon.com/sdkref/latest/guide/file-format.html">here</a>. In general the credentials file should look like this:
```
[default]
aws_access_key_id=[]
aws_secret_access_key=[]
aws_session_token=[]
```
!NOTE! if you want to change the bucket region, you have to do it inside the `AWSConfig` class, in the `s3Presigner` bean.
- Start the app with:
 - [On Windows] `./mvnw.cmd spring-boot:run`
 - [On Linux/macOS] `./mvnw spring-boot:run`
