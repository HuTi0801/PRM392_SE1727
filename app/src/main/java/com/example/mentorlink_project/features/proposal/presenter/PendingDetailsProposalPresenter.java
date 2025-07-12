package com.example.mentorlink_project.features.proposal.presenter;

import com.example.mentorlink_project.data.entities.ProjectEntity;
import com.example.mentorlink_project.data.repositories.ProjectRepository;
import com.example.mentorlink_project.features.proposal.contract.PendingDetailsProposalContract;

import java.util.List;

public class PendingDetailsProposalPresenter implements PendingDetailsProposalContract.Presenter {

    private final PendingDetailsProposalContract.View view;
    private final ProjectRepository repository;

    public PendingDetailsProposalPresenter(PendingDetailsProposalContract.View view, ProjectRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void loadPendingProposals() {
        List<ProjectEntity> pendingList = repository.getProjectsByStatus("PENDING");
        view.showProposals(pendingList);
    }

    @Override
    public void approveProposal(ProjectEntity project) {
        project.setStatus("APPROVED");
        repository.updateStatus(project.getId(), "APPROVED");
        view.showMessage("Đề tài đã được duyệt.");
        loadPendingProposals(); // refresh lại danh sách
    }

    @Override
    public void rejectProposal(ProjectEntity project, String reason) {
        project.setStatus("REJECTED");
        project.setRejectReason(reason);
        repository.updateStatusAndRejectReason(project.getId(), "REJECTED", reason);
        view.showMessage("Đã từ chối đề tài.");
        loadPendingProposals(); // refresh lại danh sách
    }
}
