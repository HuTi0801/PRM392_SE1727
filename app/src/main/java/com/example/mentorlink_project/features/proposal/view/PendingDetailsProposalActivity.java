package com.example.mentorlink_project.features.proposal.view;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mentorlink_project.R;
import com.example.mentorlink_project.data.entities.ProjectEntity;
import com.example.mentorlink_project.data.repositories.ProjectRepository;
import com.example.mentorlink_project.features.proposal.adapter.PendingDetailProposalAdapter;
import com.example.mentorlink_project.features.proposal.contract.PendingDetailsProposalContract;
import com.example.mentorlink_project.features.proposal.presenter.PendingDetailsProposalPresenter;

import java.util.List;

public class PendingDetailsProposalActivity extends AppCompatActivity implements PendingDetailsProposalContract.View {

    private ListView lvProposals;
    private PendingDetailsProposalPresenter presenter;
    private PendingDetailProposalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pending_details_proposal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pending_details_proposal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvProposals = findViewById(R.id.lvProposals);
        presenter = new PendingDetailsProposalPresenter(this, new ProjectRepository(this));
        presenter.loadPendingProposals();
    }

    @Override
    public void showProposals(List<ProjectEntity> proposalList) {
        adapter = new PendingDetailProposalAdapter(this, proposalList, presenter);
        lvProposals.setAdapter(adapter);
    }

    @Override
    public void showRejectDialog(ProjectEntity project) {
        RejectReasonDialog dialog = new RejectReasonDialog(this, reason -> {
            presenter.rejectProposal(project, reason);
        });
        dialog.show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
