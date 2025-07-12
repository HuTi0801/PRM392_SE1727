package com.example.mentorlink_project.data.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mentorlink_project.data.AppDatabaseHelper;
import com.example.mentorlink_project.data.dao.ProjectDao;
import com.example.mentorlink_project.data.entities.ProjectEntity;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    private final AppDatabaseHelper dbHelper;

    public ProjectRepository(Context context) {
        dbHelper = new AppDatabaseHelper(context);
    }

    public void insertProject(ProjectEntity project) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("group_id", project.getGroupId());
        values.put("lecture_code", project.getLectureCode());
        values.put("topic", project.getTopic());
        values.put("description", project.getDescription());
        values.put("status", project.getStatus());
        values.put("created_at", project.getCreatedAt());
        values.put("reject_reason", project.getRejectReason());
        values.put("document_url", project.getDocumentUrl());
        db.insert(ProjectDao.TABLE_NAME, null, values);
        db.close();
    }

    public List<ProjectEntity> getAllProjects() {
        List<ProjectEntity> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(ProjectDao.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            ProjectEntity p = new ProjectEntity();
            p.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            p.setGroupId(cursor.getInt(cursor.getColumnIndexOrThrow("group_id")));
            p.setLectureCode(cursor.getString(cursor.getColumnIndexOrThrow("lecture_code")));
            p.setTopic(cursor.getString(cursor.getColumnIndexOrThrow("topic")));
            p.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
            p.setStatus(cursor.getString(cursor.getColumnIndexOrThrow("status")));
            p.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow("created_at")));
            p.setRejectReason(cursor.getString(cursor.getColumnIndexOrThrow("reject_reason")));
            p.setDocumentUrl(cursor.getString(cursor.getColumnIndexOrThrow("document_url")));
            list.add(p);
        }
        cursor.close();
        db.close();
        return list;
    }

    public void updateStatus(int projectId, String newStatus) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", newStatus);
        db.update(ProjectDao.TABLE_NAME, values, "id = ?", new String[]{String.valueOf(projectId)});
        db.close();
    }

    public void updateStatusAndRejectReason(int projectId, String status, String rejectReason) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);
        values.put("reject_reason", rejectReason);
        db.update(ProjectDao.TABLE_NAME, values, "id = ?", new String[]{String.valueOf(projectId)});
        db.close();
    }

    public List<ProjectEntity> getProjectsByStatus(String status) {
        List<ProjectEntity> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                ProjectDao.TABLE_NAME,
                null,
                "status = ?",
                new String[]{status},
                null, null, null
        );

        while (cursor.moveToNext()) {
            ProjectEntity p = new ProjectEntity();
            p.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            p.setGroupId(cursor.getInt(cursor.getColumnIndexOrThrow("group_id")));
            p.setLectureCode(cursor.getString(cursor.getColumnIndexOrThrow("lecture_code")));
            p.setTopic(cursor.getString(cursor.getColumnIndexOrThrow("topic")));
            p.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
            p.setStatus(cursor.getString(cursor.getColumnIndexOrThrow("status")));
            p.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow("created_at")));
            p.setRejectReason(cursor.getString(cursor.getColumnIndexOrThrow("reject_reason")));
            p.setDocumentUrl(cursor.getString(cursor.getColumnIndexOrThrow("document_url")));
            list.add(p);
        }

        cursor.close();
        db.close();
        return list;
    }
}
