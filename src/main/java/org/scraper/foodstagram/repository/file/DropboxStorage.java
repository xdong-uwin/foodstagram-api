package org.scraper.foodstagram.repository.file;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.RequestedVisibility;
import com.dropbox.core.v2.sharing.SharedLinkSettings;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Service
public class DropboxStorage implements Storage {

    private static final String APP_NAME = "foodstagram";
    private static final String ACCESS_TOKEN = "sl.CAZjBRbwiu-KFjle01bIo8bptMzvzsEBAYxK8E2yrx_5rdj3n7KXgWvBZT_6JMu97iD4YpWVPyISicsLOpVt3d26yX1woRBHK-s813gi4LGf1elNqLGnr471cOG-VA9m9e0JQ_q3CSgE2hU";

    private final DbxClientV2 client;

    public DropboxStorage() {
        DbxRequestConfig requestConfig = new DbxRequestConfig(APP_NAME);
        this.client = new DbxClientV2(requestConfig, ACCESS_TOKEN);
    }

    @Override
    public String upload(File file) {
        String fileName = UUID.randomUUID() + getFileSuffix(file.getName());
        try (InputStream in = new FileInputStream(file)) {
            uploadToDropbox(file, fileName, in);
            return generateSharedLink(fileName);
        } catch (DbxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void uploadToDropbox(File file, String fileName, InputStream in) throws IOException, DbxException {
        client.files().uploadBuilder("/" + fileName)
                .withMode(WriteMode.ADD)
                .withClientModified(new Date(file.lastModified()))
                .uploadAndFinish(in);
    }

    private String generateSharedLink(String fileName) throws DbxException {
        return client.sharing().createSharedLinkWithSettings("/" + fileName, SharedLinkSettings.newBuilder().withRequestedVisibility(RequestedVisibility.PUBLIC).build()).getUrl();
    }

    private String getFileSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
